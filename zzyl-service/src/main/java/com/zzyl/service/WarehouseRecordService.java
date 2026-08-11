package com.zzyl.service;

import com.zzyl.base.PageResponse;
import com.zzyl.dto.WarehouseRecordDto;
import com.zzyl.vo.WarehouseRecordVo;

import java.time.LocalDateTime;

/**
 * 入库登记服务
 */
public interface WarehouseRecordService {

    /**
     * 新增入库登记(含入库物资明细,待审核状态)
     */
    void add(WarehouseRecordDto dto);

    /**
     * 审核入库登记
     *
     * @param id     入库登记编号
     * @param result 审核结果 1通过 2不通过
     */
    void audit(Long id, Integer result);

    /**
     * 删除入库登记(逻辑删除)
     */
    void deleteById(Long id);

    /**
     * 根据ID查询入库登记详情(含物资明细)
     */
    WarehouseRecordVo findById(Long id);

    /**
     * 分页查询入库登记
     */
    PageResponse<WarehouseRecordVo> findByPage(int pageNum, int pageSize, Long warehouseId, String source,
                                               Integer status, LocalDateTime startTime, LocalDateTime endTime);
}
