package com.zzyl.service;

import com.zzyl.base.PageResponse;
import com.zzyl.dto.OutboundRecordDto;
import com.zzyl.vo.OutboundRecordVo;
import com.zzyl.vo.WarehouseMaterialVo;

import java.time.LocalDateTime;

/**
 * 出库登记服务
 */
public interface OutboundRecordService {

    /**
     * 新增出库登记(扣减批次库存,待审核状态)
     */
    void add(OutboundRecordDto dto);

    /**
     * 审核出库登记(不通过时回退库存)
     *
     * @param id     出库登记编号
     * @param result 审核结果 1通过 2不通过
     */
    void audit(Long id, Integer result);

    /**
     * 删除出库登记(待审核状态删除时回退库存)
     */
    void deleteById(Long id);

    /**
     * 根据ID查询出库登记详情(含物资明细)
     */
    OutboundRecordVo findById(Long id);

    /**
     * 分页查询出库登记
     */
    PageResponse<OutboundRecordVo> findByPage(int pageNum, int pageSize, Long warehouseId, String materialUse,
                                              Integer status, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 分页查询有库存的入库物资批次(出库选择用)
     */
    PageResponse<WarehouseMaterialVo> findStockByPage(int pageNum, int pageSize, Long warehouseId, String materialName);
}
