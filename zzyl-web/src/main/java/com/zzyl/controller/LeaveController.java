package com.zzyl.controller;

import com.zzyl.base.ResponseResult;
import com.zzyl.dto.LeaveDto;
import com.zzyl.dto.LeaveQueryDto;
import com.zzyl.entity.Leave;
import com.zzyl.service.LeaveService;
import com.zzyl.vo.LeaveVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leave")
@Api(tags = "请假")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/create")
    @ApiOperation(value = "申请请假", notes = "传入请假对象")
    public ResponseResult createLeave(
            @RequestBody @ApiParam(value = "请假对象", required = true) Leave leave) {
        return leaveService.createLeave(leave);
    }

    @GetMapping
    @ApiOperation(value = "请假表单查询")
    public ResponseResult<LeaveVo> getLeave(
            @RequestParam @ApiParam(value = "请假编码") String leaveCode,
            @RequestParam(required = false) @ApiParam(value = "处理人ID") String assigneeId,
            @RequestParam @ApiParam(value = "流程状态") Integer flowStatus,
            @RequestParam(required = false) @ApiParam(value = "任务id") String taskId) {
        return leaveService.getLeave(leaveCode, assigneeId, flowStatus, taskId);
    }

    @PostMapping("/submit")
    @ApiOperation(value = "提交")
    public ResponseResult submitLeave(
            @RequestBody @ApiParam(value = "请假对象", required = true) LeaveDto leaveDto) {
        return leaveService.submitLeave(leaveDto);
    }

    @PutMapping
    @ApiOperation(value = "驳回")
    public ResponseResult disapprove(
            @RequestParam @ApiParam(value = "请假编码") String leaveCode,
            @RequestParam @ApiParam(value = "驳回消息") String message,
            @RequestParam @ApiParam(value = "任务Id") String taskId) {
        return leaveService.disapprove(leaveCode, message, taskId);
    }

    @PutMapping("/reject")
    @ApiOperation(value = "审核拒绝")
    public ResponseResult auditReject(
            @RequestParam @ApiParam(value = "请假编码") String leaveCode,
            @RequestParam @ApiParam(value = "拒绝原因") String reject,
            @RequestParam @ApiParam(value = "任务Id") String taskId) {
        return leaveService.auditReject(leaveCode, reject, taskId);
    }

    @PutMapping("/revocation")
    @ApiOperation(value = "撤回")
    public ResponseResult revocation(
            @RequestParam @ApiParam(value = "请假编码") String leaveCode,
            @RequestParam @ApiParam(value = "流程状态") Integer flowStatus,
            @RequestParam @ApiParam(value = "任务Id") String taskId) {
        return leaveService.revocation(leaveCode, flowStatus, taskId);
    }

    @PutMapping("/cancel")
    @ApiOperation(value = "撤销")
    public ResponseResult cancel(
            @RequestParam @ApiParam(value = "请假编码") String leaveCode,
            @RequestParam @ApiParam(value = "任务Id") String taskId) {
        return leaveService.cancel(leaveCode, taskId);
    }

    @ApiOperation(value = "请假管理", notes = "请假管理列表页")
    @PostMapping("/selectByPage")
    public ResponseResult selectByPage(@RequestBody LeaveQueryDto leaveQueryDto) {
        return leaveService.selectByPage(leaveQueryDto);
    }
}
