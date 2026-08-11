package com.zzyl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.base.PageResponse;
import com.zzyl.mapper.WarehouseMaterialMapper;
import com.zzyl.service.InventoryService;
import com.zzyl.vo.InventoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 库存管理服务实现(库存盘点/低库存预警)
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private WarehouseMaterialMapper warehouseMaterialMapper;

    /**
     * 分页查询库存汇总(库存盘点)
     */
    @Override
    public PageResponse<InventoryVo> findByPage(int pageNum, int pageSize, String materialName, Long typeId, Long warehouseId) {
        Page<InventoryVo> page = new Page<>(pageNum, pageSize);
        IPage<InventoryVo> byPage = warehouseMaterialMapper.findInventoryByPage(page, materialName, typeId, warehouseId);
        return PageResponse.of(byPage, InventoryVo.class);
    }

    /**
     * 低库存预警列表
     */
    @Override
    public List<InventoryVo> findWarningList() {
        return warehouseMaterialMapper.findWarningList();
    }
}
