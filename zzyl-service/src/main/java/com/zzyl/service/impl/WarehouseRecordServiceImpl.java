package com.zzyl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.base.PageResponse;
import com.zzyl.dto.WarehouseRecordDto;
import com.zzyl.entity.WarehouseMaterial;
import com.zzyl.entity.WarehouseRecord;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.WarehouseMapper;
import com.zzyl.mapper.WarehouseMaterialMapper;
import com.zzyl.mapper.WarehouseRecordMapper;
import com.zzyl.service.WarehouseRecordService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.WarehouseRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 入库登记服务实现
 */
@Slf4j
@Service
@Transactional
public class WarehouseRecordServiceImpl implements WarehouseRecordService {

    /**
     * 入库状态:待审核
     */
    public static final int STATUS_STAY_AUDIT = 0;
    /**
     * 入库状态:已通过
     */
    public static final int STATUS_PASS = 1;
    /**
     * 入库状态:未通过
     */
    public static final int STATUS_NOT_PASS = 2;

    @Autowired
    private WarehouseRecordMapper warehouseRecordMapper;

    @Autowired
    private WarehouseMaterialMapper warehouseMaterialMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * 新增入库登记(含入库物资明细)
     */
    @Override
    public void add(WarehouseRecordDto dto) {
        if (ObjectUtil.isEmpty(dto.getMaterialList())) {
            throw new BaseException("入库物资不能为空");
        }
        if (ObjectUtil.isEmpty(warehouseMapper.findById(dto.getWarehouseId()))) {
            throw new BaseException("仓库不存在");
        }
        Long userId = UserThreadLocal.getMgtUserId();
        LocalDateTime now = LocalDateTime.now();
        // 保存入库登记,状态为待审核
        WarehouseRecord warehouseRecord = BeanUtil.toBean(dto, WarehouseRecord.class);
        warehouseRecord.setStatus(STATUS_STAY_AUDIT);
        if (ObjectUtil.isEmpty(warehouseRecord.getWarehouseTime())) {
            warehouseRecord.setWarehouseTime(now);
        }
        warehouseRecord.setCreateBy(userId);
        warehouseRecord.setCreateTime(now);
        warehouseRecordMapper.insert(warehouseRecord);
        // 同一物资多条明细按物资编号合并数量后入库
        Map<Long, List<WarehouseRecordDto.WarehouseMaterialDto>> materialMap = dto.getMaterialList().stream()
                .collect(Collectors.groupingBy(WarehouseRecordDto.WarehouseMaterialDto::getMaterialId));
        materialMap.forEach((materialId, dtoList) -> {
            int totalNum = dtoList.stream().mapToInt(WarehouseRecordDto.WarehouseMaterialDto::getWarehouseNum).sum();
            if (totalNum <= 0) {
                throw new BaseException("入库数量必须大于0");
            }
            WarehouseMaterial warehouseMaterial = BeanUtil.toBean(dtoList.get(0), WarehouseMaterial.class);
            warehouseMaterial.setWarehouseRecordId(warehouseRecord.getId());
            warehouseMaterial.setWarehouseNum(totalNum);
            warehouseMaterial.setInventory(totalNum);
            warehouseMaterial.setCreateBy(userId);
            warehouseMaterial.setCreateTime(now);
            warehouseMaterialMapper.insert(warehouseMaterial);
        });
    }

    /**
     * 审核入库登记
     */
    @Override
    public void audit(Long id, Integer result) {
        if (!Objects.equals(result, STATUS_PASS) && !Objects.equals(result, STATUS_NOT_PASS)) {
            throw new BaseException("审核结果不合法");
        }
        WarehouseRecord warehouseRecord = warehouseRecordMapper.findById(id);
        if (ObjectUtil.isEmpty(warehouseRecord)) {
            throw new BaseException("入库登记不存在");
        }
        if (!Objects.equals(warehouseRecord.getStatus(), STATUS_STAY_AUDIT)) {
            throw new BaseException("入库登记已审核,不能重复审核");
        }
        WarehouseRecord update = new WarehouseRecord();
        update.setId(id);
        update.setStatus(result);
        update.setUpdateBy(UserThreadLocal.getMgtUserId());
        update.setUpdateTime(LocalDateTime.now());
        warehouseRecordMapper.update(update);
    }

    /**
     * 删除入库登记(逻辑删除)
     */
    @Override
    public void deleteById(Long id) {
        WarehouseRecord warehouseRecord = warehouseRecordMapper.findById(id);
        if (ObjectUtil.isEmpty(warehouseRecord)) {
            throw new BaseException("入库登记不存在");
        }
        WarehouseRecord update = new WarehouseRecord();
        update.setId(id);
        update.setDelFlag(1);
        update.setUpdateBy(UserThreadLocal.getMgtUserId());
        update.setUpdateTime(LocalDateTime.now());
        warehouseRecordMapper.update(update);
    }

    /**
     * 根据ID查询入库登记详情(含物资明细)
     */
    @Override
    public WarehouseRecordVo findById(Long id) {
        WarehouseRecordVo vo = warehouseRecordMapper.findVoById(id);
        if (ObjectUtil.isEmpty(vo)) {
            throw new BaseException("入库登记不存在");
        }
        vo.setMaterialList(warehouseMaterialMapper.findByWarehouseRecordId(id));
        return vo;
    }

    /**
     * 分页查询入库登记
     */
    @Override
    public PageResponse<WarehouseRecordVo> findByPage(int pageNum, int pageSize, Long warehouseId, String source,
                                                      Integer status, LocalDateTime startTime, LocalDateTime endTime) {
        Page<WarehouseRecordVo> page = new Page<>(pageNum, pageSize);
        IPage<WarehouseRecordVo> byPage = warehouseRecordMapper.findByPage(page, warehouseId, source, status, startTime, endTime);
        return PageResponse.of(byPage, WarehouseRecordVo.class);
    }
}
