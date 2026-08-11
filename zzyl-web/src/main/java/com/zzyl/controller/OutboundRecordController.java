package com.zzyl.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.dto.OutboundRecordDto;
import com.zzyl.service.OutboundRecordService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.vo.OutboundRecordVo;
import com.zzyl.vo.WarehouseMaterialVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 物资出库管理
 */
@RestController
@RequestMapping("/outbound-record")
@Api(tags = "物资出库管理")
public class OutboundRecordController extends BaseController {

    @Autowired
    private OutboundRecordService outboundRecordService;

    @PostMapping
    @ApiOperation("新增出库登记")
    public ResponseResult add(@RequestBody OutboundRecordDto dto) {
        outboundRecordService.add(dto);
        return success();
    }

    @PutMapping("/{id}/audit/{result}")
    @ApiOperation("审核出库登记(1通过 2不通过)")
    public ResponseResult audit(@PathVariable Long id, @PathVariable Integer result) {
        outboundRecordService.audit(id, result);
        return success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除出库登记")
    public ResponseResult deleteById(@PathVariable Long id) {
        outboundRecordService.deleteById(id);
        return success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询出库登记详情")
    public ResponseResult<OutboundRecordVo> findById(@PathVariable Long id) {
        return success(outboundRecordService.findById(id));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询出库登记")
    public ResponseResult<PageResponse<OutboundRecordVo>> findByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                                     @RequestParam(required = false) Long warehouseId,
                                                                     @RequestParam(required = false) String materialUse,
                                                                     @RequestParam(required = false) Integer status,
                                                                     @RequestParam(required = false) Long startTime,
                                                                     @RequestParam(required = false) Long endTime) {
        return success(outboundRecordService.findByPage(pageNum, pageSize, warehouseId, materialUse, status,
                ObjectUtil.isEmpty(startTime) ? null : LocalDateTimeUtil.of(startTime),
                ObjectUtil.isEmpty(endTime) ? null : LocalDateTimeUtil.of(endTime)));
    }

    @GetMapping("/stock/page")
    @ApiOperation("分页查询有库存的入库物资批次(出库选择用)")
    public ResponseResult<PageResponse<WarehouseMaterialVo>> findStockByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                                             @RequestParam(required = false) Long warehouseId,
                                                                             @RequestParam(required = false) String materialName) {
        return success(outboundRecordService.findStockByPage(pageNum, pageSize, warehouseId, materialName));
    }
}
