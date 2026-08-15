
package com.zzyl.controller;

import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.dto.NursingElderDto;
import com.zzyl.service.ElderService;
import com.zzyl.vo.retreat.ElderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客户老人关联 Controller
 */
@Validated
@RestController
@RequestMapping("/elder")
@Api(tags = "老人")
public class ElderController {

    @Resource
    private ElderService elderService;

    /**
     * 列表
     */
    @GetMapping("/selectList")
    @ApiOperation(value = "列表")
    public ResponseResult selectList() {
        List<ElderVo> elderVos = elderService.selectList();
        return ResponseResult.success(elderVos);
    }


    /**
     * 选择老人列表
     */
    @GetMapping("/selectListByPage")
    @ApiOperation(value = "选择老人列表")
    public ResponseResult selectListByPage(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String idCardNo,
                                           @Min(value = 1, message = "页码最小为1") @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @Min(value = 1, message = "每页条数最小为1") @Max(value = 100, message = "每页条数最大为100") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageResponse pageResponse = elderService.selectListByPage(name,idCardNo,pageNum,pageSize);
        return ResponseResult.success(pageResponse);
    }

    /**
     * 设置护理员
     * @param nursingElders
     */
    @PutMapping("/setNursing")
    @ApiOperation(value = "设置护理员", notes = "设置护理员")
    public ResponseResult setNursing(@RequestBody List<NursingElderDto> nursingElders) {
        elderService.setNursing(nursingElders);
        return ResponseResult.success();
    }


    @GetMapping("/selectByIdCard")
    @ApiOperation(value = "身份证号", notes = "身份证号")
    public ResponseResult selectByIdCard(@Pattern(regexp = "(^\\d{15}$)|(^\\d{17}[0-9Xx]$)", message = "身份证号格式不正确") @RequestParam String idCard) {
        return ResponseResult.success(elderService.selectByIdCard(idCard));
    }
}


