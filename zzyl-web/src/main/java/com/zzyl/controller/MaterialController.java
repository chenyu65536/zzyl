package com.zzyl.controller;

import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.dto.MaterialDto;
import com.zzyl.dto.MaterialTypeDto;
import com.zzyl.service.MaterialService;
import com.zzyl.vo.MaterialTypeVo;
import com.zzyl.vo.MaterialVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物资管理(含物资类别)
 */
@RestController
@RequestMapping("/material")
@Api(tags = "物资管理")
public class MaterialController extends BaseController {

    @Autowired
    private MaterialService materialService;

    // ==================== 物资类别 ====================

    @PostMapping("/type")
    @ApiOperation("新增物资类别")
    public ResponseResult addType(@Validated @RequestBody MaterialTypeDto dto) {
        materialService.addType(dto);
        return success();
    }

    @PutMapping("/type/{id}")
    @ApiOperation("更新物资类别")
    public ResponseResult updateType(@PathVariable Long id, @Validated @RequestBody MaterialTypeDto dto) {
        materialService.updateType(id, dto);
        return success();
    }

    @DeleteMapping("/type/{id}")
    @ApiOperation("删除物资类别")
    public ResponseResult deleteTypeById(@PathVariable Long id) {
        materialService.deleteTypeById(id);
        return success();
    }

    @GetMapping("/type/{id}")
    @ApiOperation("根据ID查询物资类别")
    public ResponseResult<MaterialTypeVo> findTypeById(@PathVariable Long id) {
        return success(materialService.findTypeById(id));
    }

    @GetMapping("/type/all")
    @ApiOperation("查询所有物资类别(下拉)")
    public ResponseResult<List<MaterialTypeVo>> findAllTypes(@RequestParam(required = false) String name) {
        return success(materialService.findAllTypes(name));
    }

    // ==================== 物资 ====================

    @PostMapping
    @ApiOperation("新增物资")
    public ResponseResult add(@Validated @RequestBody MaterialDto dto) {
        materialService.add(dto);
        return success();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新物资")
    public ResponseResult update(@PathVariable Long id, @Validated @RequestBody MaterialDto dto) {
        materialService.update(id, dto);
        return success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除物资")
    public ResponseResult deleteById(@PathVariable Long id) {
        materialService.deleteById(id);
        return success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询物资")
    public ResponseResult<MaterialVo> findById(@PathVariable Long id) {
        return success(materialService.findById(id));
    }

    @GetMapping("/all")
    @ApiOperation("查询所有物资(下拉)")
    public ResponseResult<List<MaterialVo>> findAll(@RequestParam(required = false) String name) {
        return success(materialService.findAll(name));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询物资")
    public ResponseResult<PageResponse<MaterialVo>> findByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                               @RequestParam(defaultValue = "10") int pageSize,
                                                               @RequestParam(required = false) String name,
                                                               @RequestParam(required = false) Long typeId) {
        return success(materialService.findByPage(pageNum, pageSize, name, typeId));
    }
}
