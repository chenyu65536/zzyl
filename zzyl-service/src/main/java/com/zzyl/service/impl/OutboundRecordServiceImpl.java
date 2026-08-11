package com.zzyl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.base.PageResponse;
import com.zzyl.dto.OutboundRecordDto;
import com.zzyl.entity.OutboundMaterial;
import com.zzyl.entity.OutboundRecord;
import com.zzyl.entity.WarehouseMaterial;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.OutboundMaterialMapper;
import com.zzyl.mapper.OutboundRecordMapper;
import com.zzyl.mapper.WarehouseMapper;
import com.zzyl.mapper.WarehouseMaterialMapper;
import com.zzyl.service.OutboundRecordService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.OutboundRecordVo;
import com.zzyl.vo.WarehouseMaterialVo;
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
 * 出库登记服务实现
 */
@Slf4j
@Service
@Transactional
public class OutboundRecordServiceImpl implements OutboundRecordService {

    /**
     * 出库状态:待审核
     */
    public static final int STATUS_STAY_AUDIT = 0;
    /**
     * 出库状态:已通过
     */
    public static final int STATUS_PASS = 1;
    /**
     * 出库状态:未通过
     */
    public static final int STATUS_NOT_PASS = 2;

    @Autowired
    private OutboundRecordMapper outboundRecordMapper;

    @Autowired
    private OutboundMaterialMapper outboundMaterialMapper;

    @Autowired
    private WarehouseMaterialMapper warehouseMaterialMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * 新增出库登记(按批次扣减库存)
     */
    @Override
    public void add(OutboundRecordDto dto) {
        if (ObjectUtil.isEmpty(dto.getMaterialList())) {
            throw new BaseException("出库物资不能为空");
        }
        if (ObjectUtil.isEmpty(warehouseMapper.findById(dto.getWarehouseId()))) {
            throw new BaseException("仓库不存在");
        }
        Long userId = UserThreadLocal.getMgtUserId();
        LocalDateTime now = LocalDateTime.now();
        // 保存出库登记,状态为待审核
        OutboundRecord outboundRecord = BeanUtil.toBean(dto, OutboundRecord.class);
        outboundRecord.setStatus(STATUS_STAY_AUDIT);
        if (ObjectUtil.isEmpty(outboundRecord.getOutboundTime())) {
            outboundRecord.setOutboundTime(now);
        }
        outboundRecord.setCreateBy(userId);
        outboundRecord.setCreateTime(now);
        outboundRecordMapper.insert(outboundRecord);
        // 同一批次多条明细合并数量
        Map<Long, List<OutboundRecordDto.OutboundMaterialDto>> materialMap = dto.getMaterialList().stream()
                .collect(Collectors.groupingBy(OutboundRecordDto.OutboundMaterialDto::getWarehouseMaterialId));
        List<WarehouseMaterial> warehouseMaterials = warehouseMaterialMapper.findByIds(materialMap.keySet().stream().collect(Collectors.toList()));
        materialMap.forEach((warehouseMaterialId, dtoList) -> {
            int outboundNum = dtoList.stream().mapToInt(OutboundRecordDto.OutboundMaterialDto::getOutboundNum).sum();
            if (outboundNum <= 0) {
                throw new BaseException("出库数量必须大于0");
            }
            WarehouseMaterial warehouseMaterial = warehouseMaterials.stream()
                    .filter(wm -> Objects.equals(wm.getId(), warehouseMaterialId))
                    .findFirst()
                    .orElseThrow(() -> new BaseException("入库物资批次不存在"));
            // 校验出库数量不能超过批次剩余库存
            if (outboundNum > warehouseMaterial.getInventory()) {
                throw new BaseException("出库数量超过批次剩余库存");
            }
            // 保存出库物资明细
            OutboundMaterial outboundMaterial = new OutboundMaterial();
            outboundMaterial.setOutboundRecordId(outboundRecord.getId());
            outboundMaterial.setWarehouseMaterialId(warehouseMaterialId);
            outboundMaterial.setMaterialId(warehouseMaterial.getMaterialId());
            outboundMaterial.setOutboundNum(outboundNum);
            outboundMaterial.setCreateBy(userId);
            outboundMaterial.setCreateTime(now);
            outboundMaterialMapper.insert(outboundMaterial);
            // 扣减批次库存
            warehouseMaterialMapper.updateInventory(warehouseMaterialId, warehouseMaterial.getInventory() - outboundNum);
        });
    }

    /**
     * 审核出库登记(不通过时回退库存)
     */
    @Override
    public void audit(Long id, Integer result) {
        if (!Objects.equals(result, STATUS_PASS) && !Objects.equals(result, STATUS_NOT_PASS)) {
            throw new BaseException("审核结果不合法");
        }
        OutboundRecord outboundRecord = outboundRecordMapper.findById(id);
        if (ObjectUtil.isEmpty(outboundRecord)) {
            throw new BaseException("出库登记不存在");
        }
        if (!Objects.equals(outboundRecord.getStatus(), STATUS_STAY_AUDIT)) {
            throw new BaseException("出库登记已审核,不能重复审核");
        }
        OutboundRecord update = new OutboundRecord();
        update.setId(id);
        update.setStatus(result);
        update.setUpdateBy(UserThreadLocal.getMgtUserId());
        update.setUpdateTime(LocalDateTime.now());
        outboundRecordMapper.update(update);
        // 审核不通过,回退该出库记录的所有物资库存
        if (Objects.equals(result, STATUS_NOT_PASS)) {
            rollbackMaterial(id);
        }
    }

    /**
     * 删除出库登记(待审核状态删除时回退库存)
     */
    @Override
    public void deleteById(Long id) {
        OutboundRecord outboundRecord = outboundRecordMapper.findById(id);
        if (ObjectUtil.isEmpty(outboundRecord)) {
            throw new BaseException("出库登记不存在");
        }
        OutboundRecord update = new OutboundRecord();
        update.setId(id);
        update.setDelFlag(1);
        update.setUpdateBy(UserThreadLocal.getMgtUserId());
        update.setUpdateTime(LocalDateTime.now());
        outboundRecordMapper.update(update);
        // 待审核状态删除,回退库存
        if (Objects.equals(outboundRecord.getStatus(), STATUS_STAY_AUDIT)) {
            rollbackMaterial(id);
        }
    }

    /**
     * 根据ID查询出库登记详情(含物资明细)
     */
    @Override
    public OutboundRecordVo findById(Long id) {
        OutboundRecordVo vo = outboundRecordMapper.findVoById(id);
        if (ObjectUtil.isEmpty(vo)) {
            throw new BaseException("出库登记不存在");
        }
        vo.setMaterialList(outboundMaterialMapper.findVoByOutboundRecordId(id));
        return vo;
    }

    /**
     * 分页查询出库登记
     */
    @Override
    public PageResponse<OutboundRecordVo> findByPage(int pageNum, int pageSize, Long warehouseId, String materialUse,
                                                     Integer status, LocalDateTime startTime, LocalDateTime endTime) {
        Page<OutboundRecordVo> page = new Page<>(pageNum, pageSize);
        IPage<OutboundRecordVo> byPage = outboundRecordMapper.findByPage(page, warehouseId, materialUse, status, startTime, endTime);
        return PageResponse.of(byPage, OutboundRecordVo.class);
    }

    /**
     * 分页查询有库存的入库物资批次(出库选择用)
     */
    @Override
    public PageResponse<WarehouseMaterialVo> findStockByPage(int pageNum, int pageSize, Long warehouseId, String materialName) {
        Page<WarehouseMaterialVo> page = new Page<>(pageNum, pageSize);
        IPage<WarehouseMaterialVo> byPage = warehouseMaterialMapper.findStockByPage(page, warehouseId, materialName);
        return PageResponse.of(byPage, WarehouseMaterialVo.class);
    }

    /**
     * 回退出库记录的所有物资库存
     */
    private void rollbackMaterial(Long outboundRecordId) {
        List<OutboundMaterial> outboundMaterials = outboundMaterialMapper.findByOutboundRecordId(outboundRecordId);
        outboundMaterials.forEach(outboundMaterial -> {
            WarehouseMaterial warehouseMaterial = warehouseMaterialMapper.findById(outboundMaterial.getWarehouseMaterialId());
            if (ObjectUtil.isNotEmpty(warehouseMaterial)) {
                warehouseMaterialMapper.updateInventory(warehouseMaterial.getId(),
                        warehouseMaterial.getInventory() + outboundMaterial.getOutboundNum());
            }
        });
    }
}
