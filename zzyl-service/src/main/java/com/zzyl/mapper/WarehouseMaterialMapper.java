package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.WarehouseMaterial;
import com.zzyl.vo.InventoryVo;
import com.zzyl.vo.WarehouseMaterialVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarehouseMaterialMapper {

    int insert(WarehouseMaterial warehouseMaterial);

    int updateInventory(@Param("id") Long id, @Param("inventory") Integer inventory);

    WarehouseMaterial findById(Long id);

    List<WarehouseMaterial> findByIds(@Param("ids") List<Long> ids);

    List<WarehouseMaterialVo> findByWarehouseRecordId(Long warehouseRecordId);

    /**
     * 分页查询有库存的入库物资(出库选择用)
     */
    IPage<WarehouseMaterialVo> findStockByPage(Page<WarehouseMaterialVo> page,
                                               @Param("warehouseId") Long warehouseId,
                                               @Param("materialName") String materialName);

    /**
     * 按物资汇总库存(库存盘点)
     */
    IPage<InventoryVo> findInventoryByPage(Page<InventoryVo> page,
                                           @Param("materialName") String materialName,
                                           @Param("typeId") Long typeId,
                                           @Param("warehouseId") Long warehouseId);

    /**
     * 低库存预警列表:物资总库存低于预警阈值
     */
    List<InventoryVo> findWarningList();

    /**
     * 统计仓库剩余库存总量(删除仓库前校验)
     */
    Long sumInventoryByWarehouseId(Long warehouseId);
}
