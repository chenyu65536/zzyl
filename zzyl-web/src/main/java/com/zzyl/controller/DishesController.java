package com.zzyl.controller;

import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.dto.DishesDto;
import com.zzyl.dto.DishesTypeDto;
import com.zzyl.service.DishesService;
import com.zzyl.vo.DishesTypeVo;
import com.zzyl.vo.DishesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理(含菜品类别)
 */
@RestController
@RequestMapping("/dishes")
@Api(tags = "菜品管理")
public class DishesController extends BaseController {

    @Autowired
    private DishesService dishesService;

    // ==================== 菜品类别 ====================

    @PostMapping("/type")
    @ApiOperation("新增菜品类别")
    public ResponseResult addType(@RequestBody DishesTypeDto dto) {
        dishesService.addType(dto);
        return success();
    }

    @PutMapping("/type/{id}")
    @ApiOperation("更新菜品类别")
    public ResponseResult updateType(@PathVariable Long id, @RequestBody DishesTypeDto dto) {
        dishesService.updateType(id, dto);
        return success();
    }

    @DeleteMapping("/type/{id}")
    @ApiOperation("删除菜品类别")
    public ResponseResult deleteTypeById(@PathVariable Long id) {
        dishesService.deleteTypeById(id);
        return success();
    }

    @GetMapping("/type/{id}")
    @ApiOperation("根据ID查询菜品类别")
    public ResponseResult<DishesTypeVo> findTypeById(@PathVariable Long id) {
        return success(dishesService.findTypeById(id));
    }

    @GetMapping("/type/all")
    @ApiOperation("查询所有菜品类别(下拉)")
    public ResponseResult<List<DishesTypeVo>> findAllTypes(@RequestParam(required = false) String name) {
        return success(dishesService.findAllTypes(name));
    }

    // ==================== 菜品 ====================

    @PostMapping
    @ApiOperation("新增菜品")
    public ResponseResult add(@RequestBody DishesDto dto) {
        dishesService.add(dto);
        return success();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新菜品")
    public ResponseResult update(@PathVariable Long id, @RequestBody DishesDto dto) {
        dishesService.update(id, dto);
        return success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除菜品")
    public ResponseResult deleteById(@PathVariable Long id) {
        dishesService.deleteById(id);
        return success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询菜品")
    public ResponseResult<DishesVo> findById(@PathVariable Long id) {
        return success(dishesService.findById(id));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询菜品")
    public ResponseResult<PageResponse<DishesVo>> findByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                             @RequestParam(defaultValue = "10") int pageSize,
                                                             @RequestParam(required = false) String name,
                                                             @RequestParam(required = false) Long typeId) {
        return success(dishesService.findByPage(pageNum, pageSize, name, typeId));
    }
}
