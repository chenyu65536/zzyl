package com.zzyl.controller;

import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.dto.WarehouseDto;
import com.zzyl.service.WarehouseService;
import com.zzyl.vo.WarehouseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库管理
 */
@RestController
@RequestMapping("/warehouse")
@Api(tags = "仓库管理")
public class WarehouseController extends BaseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    @ApiOperation("新增仓库")
    public ResponseResult add(@Validated @RequestBody WarehouseDto dto) {
        warehouseService.add(dto);
        return success();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新仓库")
    public ResponseResult update(@PathVariable Long id, @Validated @RequestBody WarehouseDto dto) {
        warehouseService.update(id, dto);
        return success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除仓库")
    public ResponseResult deleteById(@PathVariable Long id) {
        warehouseService.deleteById(id);
        return success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询仓库")
    public ResponseResult<WarehouseVo> findById(@PathVariable Long id) {
        return success(warehouseService.findById(id));
    }

    @GetMapping("/all")
    @ApiOperation("查询所有仓库(下拉)")
    public ResponseResult<List<WarehouseVo>> findAll() {
        return success(warehouseService.findAll());
    }

    @GetMapping("/page")
    @ApiOperation("分页查询仓库")
    public ResponseResult<PageResponse<WarehouseVo>> findByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                                @RequestParam(defaultValue = "10") int pageSize,
                                                                @RequestParam(required = false) String name) {
        return success(warehouseService.findByPage(pageNum, pageSize, name));
    }
}
