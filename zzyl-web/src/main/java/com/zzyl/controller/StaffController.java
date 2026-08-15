package com.zzyl.controller;

import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.dto.StaffDto;
import com.zzyl.service.StaffService;
import com.zzyl.vo.StaffVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/staff")
@Api(tags = "员工管理")
public class StaffController extends BaseController {

    @Autowired
    private StaffService staffService;

    @PostMapping
    @ApiOperation("新增员工")
    public ResponseResult add(@Validated @RequestBody StaffDto dto) {
        staffService.add(dto);
        return success();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新员工")
    public ResponseResult update(@PathVariable Long id, @Validated @RequestBody StaffDto dto) {
        staffService.update(id, dto);
        return success();
    }

    @PutMapping("/{id}/leave")
    @ApiOperation("员工离职")
    public ResponseResult leave(@PathVariable Long id) {
        staffService.leave(id);
        return success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据ID查询员工")
    public ResponseResult<StaffVo> findById(@PathVariable Long id) {
        return success(staffService.findById(id));
    }

    @GetMapping("/all")
    @ApiOperation("查询在职员工(下拉)")
    public ResponseResult<List<StaffVo>> findAll(@RequestParam(required = false) String name) {
        return success(staffService.findAll(name));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询员工")
    public ResponseResult<PageResponse<StaffVo>> findByPage(@RequestParam(defaultValue = "1") int pageNum,
                                                            @RequestParam(defaultValue = "10") int pageSize,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String phone,
                                                            @RequestParam(required = false) String leaveFlag) {
        return success(staffService.findByPage(pageNum, pageSize, name, phone, leaveFlag));
    }
}
