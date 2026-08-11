package com.zzyl.service;

import com.zzyl.base.PageResponse;
import com.zzyl.vo.InventoryVo;

import java.util.List;

/**
 * 库存管理服务(库存盘点/低库存预警)
 */
public interface InventoryService {

    /**
     * 分页查询库存汇总(库存盘点)
     */
    PageResponse<InventoryVo> findByPage(int pageNum, int pageSize, String materialName, Long typeId, Long warehouseId);

    /**
     * 低库存预警列表
     */
    List<InventoryVo> findWarningList();
}
