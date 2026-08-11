package com.zzyl.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.dto.WarehouseRecordDto;
import com.zzyl.service.WarehouseRecordService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.vo.WarehouseRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 物资入库管理
 */
@RestController
@RequestMapping("/warehouse-record")
@Api(tags = "物资入库管理")
public class WarehouseRecordController extends BaseController {

    @Autowired
    private WarehouseRecordService warehouseRecordService;

    @PostMapping
    @ApiOperation("新增入库登记")
    public ResponseResult add(@RequestBody WarehouseRecordDto dto) {
        warehouseRecordService.add(dto);
        return success();
    }

    @PutMapping("/{id}/audit/{result}")
    @ApiOperation("审核入库登记(1通过 2不通过)")
    public ResponseResult audit(@PathVariable Long id, @PathVariable Integer result) {
        warehouseRecordService.audit(id, result);
        return success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除入库登记")
    public ResponseResult deleteById(@PathVariable Long id) {
        warehouseRecordService.deleteById(id);
        return success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询入库登记详情")
    public ResponseResult<WarehouseRecordVo> findById(@PathVariable Long id) {
        return success(warehouseRecordService.findById(id));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询入库登记")
    public ResponseResult<PageResponse<WarehouseRecordVo>> findByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                                      @RequestParam(defaultValue = "10") int pageSize,
                                                                      @RequestParam(required = false) Long warehouseId,
                                                                      @RequestParam(required = false) String source,
                                                                      @RequestParam(required = false) Integer status,
                                                                      @RequestParam(required = false) Long startTime,
                                                                      @RequestParam(required = false) Long endTime) {
        return success(warehouseRecordService.findByPage(pageNum, pageSize, warehouseId, source, status,
                ObjectUtil.isEmpty(startTime) ? null : LocalDateTimeUtil.of(startTime),
                ObjectUtil.isEmpty(endTime) ? null : LocalDateTimeUtil.of(endTime)));
    }
}
