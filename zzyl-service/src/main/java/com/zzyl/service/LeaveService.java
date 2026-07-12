package com.zzyl.service;

import com.zzyl.base.ResponseResult;
import com.zzyl.dto.LeaveDto;
import com.zzyl.dto.LeaveQueryDto;
import com.zzyl.entity.Leave;
import com.zzyl.vo.LeaveVo;

/**
 * 请假Service接口
 */
public interface LeaveService {

    /**
     * 请假申请
     * @param leave 请假对象
     * @return 操作结果
     */
    ResponseResult createLeave(Leave leave);

    /**
     * 查询请假信息
     * @param leaveCode 请假编码
     * @param assigneeId 处理人ID
     * @param flowStatus 流程状态
     * @param taskId 任务id
     * @return 请假详情
     */
    ResponseResult<LeaveVo> getLeave(String leaveCode, String assigneeId, Integer flowStatus, String taskId);

    /**
     * 提交请假申请（审批流转）
     * @param leaveDto 请假DTO
     * @return 操作结果
     */
    ResponseResult submitLeave(LeaveDto leaveDto);

    /**
     * 审核拒绝
     * @param leaveCode 请假编码
     * @param reject 拒绝原因
     * @param taskId 任务Id
     * @return 操作结果
     */
    ResponseResult auditReject(String leaveCode, String reject, String taskId);

    /**
     * 撤回
     * @param leaveCode 请假编码
     * @param flowStatus 流程状态
     * @param taskId 任务Id
     * @return 操作结果
     */
    ResponseResult revocation(String leaveCode, Integer flowStatus, String taskId);

    /**
     * 驳回
     * @param leaveCode 请假编码
     * @param message 驳回消息
     * @param taskId 任务Id
     * @return 操作结果
     */
    ResponseResult disapprove(String leaveCode, String message, String taskId);

    /**
     * 撤销
     * @param leaveCode 请假编码
     * @param taskId 任务Id
     * @return 操作结果
     */
    ResponseResult cancel(String leaveCode, String taskId);

    /**
     * 请假管理列表查询
     * @param leaveQueryDto 查询条件
     * @return 分页结果
     */
    ResponseResult selectByPage(LeaveQueryDto leaveQueryDto);
}
