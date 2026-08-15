package com.zzyl.controller;

import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.dto.BuildingDto;
import com.zzyl.service.BuildingService;
import com.zzyl.vo.BuildingVo;
import com.zzyl.vo.FloorVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 楼栋管理
 */
@RestController
@RequestMapping("/building")
@Api(tags = "楼栋管理")
public class BuildingController extends BaseController {

    @Autowired
    private BuildingService buildingService;

    @PostMapping
    @ApiOperation("新增楼栋")
    public ResponseResult add(@Validated @RequestBody BuildingDto dto) {
        buildingService.add(dto);
        return success();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新楼栋")
    public ResponseResult update(@PathVariable Long id, @Validated @RequestBody BuildingDto dto) {
        buildingService.update(id, dto);
        return success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除楼栋")
    public ResponseResult deleteById(@PathVariable Long id) {
        buildingService.deleteById(id);
        return success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询楼栋")
    public ResponseResult<BuildingVo> findById(@PathVariable Long id) {
        return success(buildingService.findById(id));
    }

    @GetMapping("/all")
    @ApiOperation("查询所有楼栋(下拉)")
    public ResponseResult<List<BuildingVo>> findAll() {
        return success(buildingService.findAll());
    }

    @GetMapping("/page")
    @ApiOperation("分页查询楼栋")
    public ResponseResult<PageResponse<BuildingVo>> findByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                               @RequestParam(defaultValue = "10") int pageSize,
                                                               @RequestParam(required = false) String name) {
        return success(buildingService.findByPage(pageNum, pageSize, name));
    }

    @GetMapping("/{id}/floors")
    @ApiOperation("查询楼栋下的楼层列表")
    public ResponseResult<List<FloorVo>> findFloorsByBuildingId(@PathVariable Long id) {
        return success(buildingService.findFloorsByBuildingId(id));
    }
}
