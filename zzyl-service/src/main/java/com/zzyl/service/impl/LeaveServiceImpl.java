package com.zzyl.service.impl;

import com.alibaba.fastjson.JSON;
import com.zzyl.base.PageResponse;
import com.zzyl.base.ResponseResult;
import com.zzyl.constant.AccraditationRecordConstant;
import com.zzyl.constant.PendingTasksConstant;
import com.zzyl.dto.LeaveDto;
import com.zzyl.dto.LeaveQueryDto;
import com.zzyl.entity.AccraditationRecord;
import com.zzyl.entity.Elder;
import com.zzyl.entity.Leave;
import com.zzyl.entity.User;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.AccraditationRecordMapper;
import com.zzyl.mapper.ElderMapper;
import com.zzyl.mapper.LeaveMapper;
import com.zzyl.service.AccraditationRecordService;
import com.zzyl.service.ActFlowCommService;
import com.zzyl.service.LeaveService;
import com.zzyl.utils.CodeUtil;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.LeaveVo;
import com.zzyl.vo.RecoreVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请假Service实现类
 */
@Slf4j
@Service
public class LeaveServiceImpl implements LeaveService {

    private static final String LEAVE_CODE_PREFIX = "QJ";

    @Autowired
    private LeaveMapper leaveMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private AccraditationRecordMapper accraditationRecordMapper;

    @Autowired
    private ActFlowCommService actFlowCommService;

    @Autowired
    private AccraditationRecordService accraditationRecordService;

    @Autowired
    private ElderMapper elderMapper;

    @Autowired
    private com.zzyl.mapper.DeptMapper deptMapper;

    @Autowired
    private com.zzyl.mapper.UserMapper userMapper;

    /**
     * 请假申请
     */
    @Override
    @Transactional
    public ResponseResult createLeave(Leave leave) {

        // 验证是否已在请假中
        Long elderId = leave.getElderId();
        Leave dbLeave = leaveMapper.selectByElderId(elderId);
        if (dbLeave != null && leave.getFlowStatus() == null) {
            return ResponseResult.error(dbLeave.getName() + "已发起请假申请");
        }

        if (ObjectUtil.isNotEmpty(leave.getTaskId())) {
            // 修改模式：不重复修改老人状态
            Leave oldLeave = leaveMapper.getLeaveByCode(leave.getLeaveCode());
            if (!oldLeave.getElderId().equals(leave.getElderId())) {
                Elder elder = new Elder();
                elder.setId(leave.getElderId());
                elder.setStatus(1);
                elderMapper.updateByPrimaryKeySelective(elder);
            }
        }

        // 从当前线程获取登录用户
        String subject = UserThreadLocal.getSubject();
        User user = JSON.parseObject(subject, User.class);

        // 请假标题
        String title = leave.getName() + "的请假申请";
        leave.setTitle(title);
        leave.setFlowStatus(Leave.FlowStatus.APPLY.getCode());
        leave.setStatus(Leave.Status.APPLICATION.getCode());

        if (leave.getLeaveCode() != null) {
            // 修改模式
            if (!user.getId().equals(leave.getApplicatId())) {
                return ResponseResult.error("不是申请人，不能提交数据");
            }
            leaveMapper.update(leave);
        } else {
            // 新增模式
            leave.setApplicat(user.getRealName());
            leave.setApplicatId(user.getId());
            leave.setCreateTime(LocalDateTime.now());
            leave.setCreateBy(user.getId());

            // 生成请假单号
            String leaveCode = CodeUtil.generateCode(LEAVE_CODE_PREFIX, redisTemplate, 5);
            leave.setLeaveCode(leaveCode);

            // 申请人部门编号
            String deptNo = user.getDeptNo();
            leave.setDeptNo(deptNo);

            leaveMapper.createLeave(leave);

            // 修改老人状态为请假中
            Elder elder = new Elder();
            elder.setId(leave.getElderId());
            elder.setStatus(2);
            elderMapper.updateByPrimaryKeySelective(elder);
        }

        // 完成/启动流程
        if (ObjectUtil.isNotEmpty(leave.getTaskId())) {
            actFlowCommService.completeProcess(leave.getTitle(), leave.getTaskId(), user.getId().toString(), 1, leave.getStatus());
        } else {
            Map<String, Object> variables = setvariables(leave.getLeaveCode());
            actFlowCommService.start(leave.getId(), "leave", user, variables, true);
        }

        // 保存审核记录
        Long nextAssignee = actFlowCommService.getNextAssignee("leave", "leave:" + leave.getId());
        RecoreVo recoreVo = getRecoreVo(
                leave,
                user,
                AccraditationRecordConstant.AUDIT_STATUS_PASS,
                "",
                "发起申请-申请请假",
                "护理组长审批-请假审批",
                nextAssignee,
                AccraditationRecordConstant.RECORD_HANDLE_TYPE_PROCESSED);
        accraditationRecordService.insert(recoreVo);

        return ResponseResult.success("提交成功");
    }

    /**
     * 获取操作记录数据
     */
    private RecoreVo getRecoreVo(Leave leave, User user, Integer status, String option,
                                 String step, String nextStep, Long nextAssignee, Integer handleType) {
        RecoreVo recoreVo = new RecoreVo();
        recoreVo.setId(leave.getId());
        recoreVo.setType(PendingTasksConstant.TASK_TYPE_LEAVE);
        recoreVo.setFlowStatus(leave.getFlowStatus());
        recoreVo.setStatus(status);
        recoreVo.setOption(option);
        recoreVo.setNextStep(nextStep);
        recoreVo.setNextAssignee(nextAssignee);
        recoreVo.setUserId(user.getId());
        recoreVo.setRealName(user.getRealName());
        recoreVo.setHandleType(handleType);
        recoreVo.setStep(step);
        return recoreVo;
    }

    /**
     * 设置流程变量
     */
    public Map<String, Object> setvariables(String leaveCode) {
        Map<String, Object> variables = new HashMap<>();

        Leave leave = leaveMapper.getLeaveByCode(leaveCode);
        Long applicatId = leave.getApplicatId();
        variables.put("assignee0", applicatId);
        variables.put("assignee0Name", leave.getApplicat());
        variables.put("processTitle", leave.getTitle());

        // 护理部部门编号
        com.zzyl.entity.Dept dept = deptMapper.selectByDeptNo(com.zzyl.constant.RetreatConstant.NURSING_DEPT_CODE);
        Long leaderId = dept.getLeaderId();
        variables.put("assignee1", leaderId);

        // 流程类型：请假
        variables.put("processType", PendingTasksConstant.TASK_TYPE_LEAVE);
        variables.put("processCode", leave.getLeaveCode());
        variables.put("processStatus", 1);

        return variables;
    }

    /**
     * 查询请假信息
     */
    @Override
    public ResponseResult<LeaveVo> getLeave(String leaveCode, String assigneeId, Integer flowStatus, String taskId) {

        Leave leave = leaveMapper.getLeaveByCode(leaveCode);
        List<AccraditationRecord> accraditationRecordList = accraditationRecordMapper
                .getAccraditationRecordByBuisId(leave.getId(), PendingTasksConstant.TASK_TYPE_LEAVE);

        LeaveVo vo = new LeaveVo();
        vo.setLeave(leave);
        vo.setAccraditationRecords(accraditationRecordList);
        vo.setIsShow(1);
        vo.setIsRevocation(false);

        Integer dbFlowStatus = leave.getFlowStatus();

        // 撤回逻辑：flowStatus=1（护理组长审批中），操作人是申请人，前端传的是申请状态
        if (dbFlowStatus.equals(Leave.FlowStatus.NURSE_APPROVAL.getCode())
                && leave.getApplicatId().equals(Long.valueOf(assigneeId))
                && flowStatus.equals(Leave.FlowStatus.APPLY.getCode())
                && leave.getStatus().equals(Leave.Status.APPLICATION.getCode())) {
            vo.setIsRevocation(true);
        }

        if (flowStatus < 0) {
            flowStatus = leave.getFlowStatus();
        }

        Integer isShow = 1;
        if (ObjectUtil.isNotEmpty(taskId)) {
            isShow = actFlowCommService.isCurrentUserAndStep(taskId, flowStatus, leave);
        }

        // 撤回按钮：是上一个节点的审核人
        vo.setIsRevocation(isShow == 2 && flowStatus.equals(leave.getFlowStatus() - 1)
                && leave.getStatus().equals(Leave.Status.APPLICATION.getCode()));

        if (isShow.equals(2) || isShow.equals(3)) {
            isShow = 1;
        }

        vo.setIsShow(isShow);
        vo.setType(PendingTasksConstant.TASK_TYPE_LEAVE);

        // 审批通过后不可撤回
        if (flowStatus.equals(Leave.FlowStatus.NURSE_APPROVAL.getCode())) {
            vo.setIsRevocation(false);
        }

        // 查询下一个审批人
        AccraditationRecord lastRecord = accraditationRecordMapper
                .getLastByBuisId(leave.getId(), PendingTasksConstant.TASK_TYPE_LEAVE);
        if (ObjectUtil.isNotEmpty(lastRecord)) {
            vo.setNextApprover(lastRecord.getNextApproverRole());
        }

        return ResponseResult.success(vo);
    }

    /**
     * 提交请假申请（审批流转）
     */
    @Override
    @Transactional
    public ResponseResult submitLeave(LeaveDto leaveDto) {

        String subject = UserThreadLocal.getSubject();
        User user = JSON.parseObject(subject, User.class);

        // 校验操作人
        if (leaveDto.getAssigneeId() == null || !leaveDto.getAssigneeId().equals(user.getId().toString())) {
            return ResponseResult.error("当前任务不属于你");
        }

        Leave leave = leaveMapper.getLeaveByCode(leaveDto.getCode());

        // 已完成则不能再次提交
        if (Leave.Status.FINISHED.getCode().equals(leave.getStatus())) {
            throw new BaseException("该请假申请已完成");
        }

        RecoreVo recoreVo = new RecoreVo();
        recoreVo.setRealName(user.getRealName());
        recoreVo.setUserId(user.getId());
        recoreVo.setStatus(AccraditationRecordConstant.AUDIT_STATUS_PASS);
        recoreVo.setId(leave.getId());
        recoreVo.setType(PendingTasksConstant.TASK_TYPE_LEAVE);
        recoreVo.setFlowStatus(leave.getFlowStatus());

        // 护理组长审批（formKey="1"）
        if (leave.getFlowStatus().equals(Leave.FlowStatus.NURSE_APPROVAL.getCode())) {
            recoreVo.setOption("同意");
            recoreVo.setStep("护理组长审批-请假审批");
            recoreVo.setNextStep("");
            recoreVo.setHandleType(AccraditationRecordConstant.RECORD_HANDLE_TYPE_AUDIT);

            // 更新请假状态为已完成
            leave.setStatus(Leave.Status.FINISHED.getCode());
            leaveMapper.updateStatus(leave.getId(), Leave.Status.FINISHED.getCode());

            // 更新老人状态为请假中
            Elder elder = new Elder();
            elder.setId(leave.getElderId());
            elder.setStatus(2);
            elderMapper.updateByPrimaryKeySelective(elder);
        }

        // 完成任务
        actFlowCommService.completeProcess("", leaveDto.getTaskId(), user.getId().toString(), 1, leave.getStatus());

        // 保存操作记录
        Long nextAssignee = actFlowCommService.getNextAssignee("leave", "leave:" + leave.getId());
        recoreVo.setNextAssignee(nextAssignee);
        accraditationRecordService.insert(recoreVo);

        return ResponseResult.success("提交成功");
    }

    /**
     * 审核拒绝
     */
    @Override
    @Transactional
    public ResponseResult auditReject(String leaveCode, String reject, String taskId) {

        String subject = UserThreadLocal.getSubject();
        User user = JSON.parseObject(subject, User.class);

        Leave leave = leaveMapper.getLeaveByCode(leaveCode);
        if (leave == null) {
            throw new BaseException("请假单不存在");
        }

        // 修改请假状态为已关闭
        leaveMapper.updateStatus(leave.getId(), Leave.Status.CLOSED.getCode());

        // 完成任务
        actFlowCommService.closeProcess(taskId, PendingTasksConstant.TASK_STATUS_CLOSED);

        // 保存审核记录
        RecoreVo recoreVo = getRecoreVo(leave, user,
                AccraditationRecordConstant.AUDIT_STATUS_REJECT,
                reject,
                "护理组长-请假审批",
                "",
                null,
                AccraditationRecordConstant.RECORD_HANDLE_TYPE_AUDIT);
        accraditationRecordService.insert(recoreVo);

        // 恢复老人状态
        Elder elder = new Elder();
        elder.setId(leave.getElderId());
        elder.setStatus(1);
        elderMapper.updateByPrimaryKeySelective(elder);

        return ResponseResult.success();
    }

    /**
     * 撤回
     */
    @Override
    @Transactional
    public ResponseResult revocation(String leaveCode, Integer flowStatus, String taskId) {

        String subject = UserThreadLocal.getSubject();
        User user = JSON.parseObject(subject, User.class);

        Leave leave = leaveMapper.getLeaveByCode(leaveCode);
        if (leave == null) {
            throw new BaseException("请假单不存在");
        }

        // 已审核则不能撤回
        if (leave.getFlowStatus() > flowStatus) {
            throw new BaseException("请假单已审核，不能撤回");
        }

        // 流程状态回退
        Integer state = flowStatus - 1;
        leaveMapper.updateLeaveFlowStatus(leave.getId(), state);

        // 撤回任务
        actFlowCommService.withdrawTask(taskId, false);

        // 保存审核记录
        String currentStep = "撤回处理-申请请假";
        if (leave.getFlowStatus().equals(Leave.FlowStatus.NURSE_APPROVAL.getCode())) {
            currentStep = "撤回审批-请假审批";
        }
        RecoreVo recoreVo = getRecoreVo(leave, user,
                AccraditationRecordConstant.AUDIT_STATUS_WITHDRAWS,
                "",
                currentStep,
                "",
                null,
                AccraditationRecordConstant.RECORD_HANDLE_TYPE_AUDIT);
        accraditationRecordService.insert(recoreVo);

        return ResponseResult.success();
    }

    /**
     * 驳回
     */
    @Override
    @Transactional
    public ResponseResult disapprove(String leaveCode, String message, String taskId) {

        String subject = UserThreadLocal.getSubject();
        User user = JSON.parseObject(subject, User.class);

        Leave leave = leaveMapper.getLeaveByCode(leaveCode);
        if (leave == null) {
            throw new BaseException("请假单不存在");
        }

        // 回退到申请状态
        leaveMapper.updateLeaveFlowStatus(leave.getId(), Leave.FlowStatus.APPLY.getCode());

        actFlowCommService.rollBackTask(taskId, true);

        // 保存审核记录
        RecoreVo recoreVo = getRecoreVo(leave, user,
                AccraditationRecordConstant.AUDIT_STATUS_DISAPPROVE,
                message,
                "驳回申请-请假审批",
                "",
                null,
                AccraditationRecordConstant.RECORD_HANDLE_TYPE_AUDIT);
        accraditationRecordService.insert(recoreVo);

        return ResponseResult.success();
    }

    /**
     * 撤销
     */
    @Override
    @Transactional
    public ResponseResult cancel(String leaveCode, String taskId) {
        String subject = UserThreadLocal.getSubject();
        User user = JSON.parseObject(subject, User.class);

        Leave leave = leaveMapper.getLeaveByCode(leaveCode);
        if (leave == null) {
            throw new BaseException("请假单不存在");
        }

        // 流程状态回退
        leaveMapper.updateLeaveFlowStatus(leave.getId(), leave.getFlowStatus() - 1);
        // 状态改为已关闭
        leaveMapper.updateStatus(leave.getId(), Leave.Status.CLOSED.getCode());

        // 恢复老人状态
        Elder elder = new Elder();
        elder.setId(leave.getElderId());
        elder.setStatus(1);
        elderMapper.updateByPrimaryKeySelective(elder);

        actFlowCommService.closeProcess(taskId, PendingTasksConstant.TASK_STATUS_CLOSED);

        // 保存审核记录
        RecoreVo recoreVo = getRecoreVo(leave, user,
                AccraditationRecordConstant.AUDIT_STATUS_DISAPPROVE,
                "",
                "撤销申请-申请请假",
                "",
                null,
                AccraditationRecordConstant.RECORD_HANDLE_TYPE_PROCESSED);
        accraditationRecordService.insert(recoreVo);

        return ResponseResult.success();
    }

    /**
     * 请假管理列表查询
     */
    @Override
    public ResponseResult selectByPage(LeaveQueryDto leaveQueryDto) {
        com.github.pagehelper.PageHelper.startPage(leaveQueryDto.getPageNum(), leaveQueryDto.getPageSize());
        List<Leave> list = leaveMapper.selectByPage(
                leaveQueryDto.getLeaveCode(),
                leaveQueryDto.getName(),
                leaveQueryDto.getIdCardNo(),
                leaveQueryDto.getStartTime(),
                leaveQueryDto.getEndTime(),
                null);
        com.github.pagehelper.Page<Leave> page = (com.github.pagehelper.Page<Leave>) list;
        return ResponseResult.success(PageResponse.of(page, Leave.class));
    }
}
