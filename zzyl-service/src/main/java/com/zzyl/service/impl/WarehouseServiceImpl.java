package com.zzyl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.base.PageResponse;
import com.zzyl.dto.WarehouseDto;
import com.zzyl.entity.Warehouse;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.WarehouseMapper;
import com.zzyl.mapper.WarehouseMaterialMapper;
import com.zzyl.service.WarehouseService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.WarehouseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 仓库管理服务实现
 */
@Slf4j
@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private WarehouseMaterialMapper warehouseMaterialMapper;

    /**
     * 新增仓库
     */
    @Override
    public void add(WarehouseDto dto) {
        // 校验仓库名称是否已存在
        if (ObjectUtil.isNotEmpty(warehouseMapper.findByName(dto.getName()))) {
            throw new BaseException("仓库名称已存在");
        }
        Warehouse warehouse = BeanUtil.toBean(dto, Warehouse.class);
        warehouse.setCreateBy(UserThreadLocal.getMgtUserId());
        warehouse.setCreateTime(LocalDateTime.now());
        warehouseMapper.insert(warehouse);
    }

    /**
     * 更新仓库
     */
    @Override
    public void update(Long id, WarehouseDto dto) {
        Warehouse exist = warehouseMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("仓库不存在");
        }
        // 校验仓库名称是否与其他仓库重复
        Warehouse byName = warehouseMapper.findByName(dto.getName());
        if (ObjectUtil.isNotEmpty(byName) && !Objects.equals(byName.getId(), id)) {
            throw new BaseException("仓库名称已存在");
        }
        Warehouse warehouse = BeanUtil.toBean(dto, Warehouse.class);
        warehouse.setId(id);
        warehouse.setUpdateBy(UserThreadLocal.getMgtUserId());
        warehouse.setUpdateTime(LocalDateTime.now());
        warehouseMapper.update(warehouse);
    }

    /**
     * 删除仓库(逻辑删除,仓库有剩余库存时禁止删除)
     */
    @Override
    public void deleteById(Long id) {
        Warehouse exist = warehouseMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("仓库不存在");
        }
        Long inventory = warehouseMaterialMapper.sumInventoryByWarehouseId(id);
        if (inventory != null && inventory > 0) {
            throw new BaseException("仓库尚有剩余库存,不能删除");
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setId(id);
        warehouse.setDelFlag(1);
        warehouse.setUpdateBy(UserThreadLocal.getMgtUserId());
        warehouse.setUpdateTime(LocalDateTime.now());
        warehouseMapper.update(warehouse);
    }

    /**
     * 根据ID查询仓库
     */
    @Override
    public WarehouseVo findById(Long id) {
        Warehouse warehouse = warehouseMapper.findById(id);
        if (ObjectUtil.isEmpty(warehouse)) {
            return null;
        }
        return BeanUtil.toBean(warehouse, WarehouseVo.class);
    }

    /**
     * 查询所有仓库(下拉)
     */
    @Override
    public List<WarehouseVo> findAll() {
        return warehouseMapper.findAll().stream()
                .map(warehouse -> BeanUtil.toBean(warehouse, WarehouseVo.class))
                .collect(Collectors.toList());
    }

    /**
     * 分页查询仓库
     */
    @Override
    public PageResponse<WarehouseVo> findByPage(int pageNum, int pageSize, String name) {
        Page<WarehouseVo> page = new Page<>(pageNum, pageSize);
        IPage<WarehouseVo> byPage = warehouseMapper.findByPage(page, name);
        return PageResponse.of(byPage, WarehouseVo.class);
    }
}
