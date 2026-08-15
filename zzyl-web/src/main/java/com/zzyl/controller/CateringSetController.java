package com.zzyl.controller;

import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.dto.CateringSetDto;
import com.zzyl.service.CateringSetService;
import com.zzyl.vo.CateringSetVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 餐饮套餐管理
 */
@RestController
@RequestMapping("/catering-set")
@Api(tags = "餐饮套餐管理")
public class CateringSetController extends BaseController {

    @Autowired
    private CateringSetService cateringSetService;

    @PostMapping
    @ApiOperation("新增套餐")
    public ResponseResult add(@Validated @RequestBody CateringSetDto dto) {
        cateringSetService.add(dto);
        return success();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新套餐")
    public ResponseResult update(@PathVariable Long id, @Validated @RequestBody CateringSetDto dto) {
        cateringSetService.update(id, dto);
        return success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除套餐")
    public ResponseResult deleteById(@PathVariable Long id) {
        cateringSetService.deleteById(id);
        return success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询套餐详情")
    public ResponseResult<CateringSetVo> findById(@PathVariable Long id) {
        return success(cateringSetService.findById(id));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询套餐")
    public ResponseResult<PageResponse<CateringSetVo>> findByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                                  @RequestParam(defaultValue = "10") int pageSize,
                                                                  @RequestParam(required = false) String name) {
        return success(cateringSetService.findByPage(pageNum, pageSize, name));
    }
}
