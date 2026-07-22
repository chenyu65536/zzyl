import { request } from '@/utils/request'
import type { ListResult, ListModel, FormApply } from '@/api/model/synergyModel'

// ========== 类型定义 ==========

// 请假列表项
export interface LeaveListModel {
  id: number
  leaveCode: string
  title: string
  elderId: number
  name: string
  idCardNo: string
  phone: string
  nursingLevelName: string
  bedNo: string
  counselor: string
  leaveStartTime: string
  leaveEndTime: string
  leaveReason: string
  remark: string
  applicat: string
  deptNo: string
  applicatId: number
  createTime: string
  flowStatus: number
  status: number
  taskId: string
}

// 请假表单提交
export interface FormLeave {
  leaveCode: string
  title: string
  elderId: number
  name: string
  idCardNo: string
  phone: string
  nursingLevelName: string
  bedNo: string
  counselor: string
  leaveStartTime: string
  leaveEndTime: string
  leaveReason: string
  remark: string
  applicat: string
  deptNo: string
  applicatId: number
}

// 请假操作参数
export interface LeaveActionParams {
  leaveCode: string
  message?: string
  reject?: string
  flowStatus?: number
  taskId: string
}

// 请假分页查询参数
export interface LeaveQueryParams {
  leaveCode?: string
  name?: string
  idCardNo?: string
  startTime?: number
  endTime?: number
  pageNum?: number
  pageSize?: number
}

// 请假响应
export interface LeaveResult {
  data: any[]
  list: Array<LeaveListModel>
}

// ========== 请假管理 API ==========

// 申请请假
export function createLeave(params: FormLeave) {
  return request.post<ListResult>({
    url: '/leave/create',
    data: params
  })
}

// 请假表单查询
export function getLeave(params: {
  leaveCode: string
  assigneeId?: string
  flowStatus: number
  taskId?: string
}) {
  return request.get<FormApply>({
    url: '/leave',
    params
  })
}

// 提交请假
export function submitLeave(params: { code: string; assigneeId?: string; taskId?: string }) {
  return request.post<ListResult>({
    url: '/leave/submit',
    data: params
  })
}

// 驳回
export function disapproveLeave(params: LeaveActionParams) {
  return request.put<ListResult>({
    url: `/leave?leaveCode=${params.leaveCode}&message=${params.message}&taskId=${params.taskId}`
  })
}

// 审核拒绝
export function rejectLeave(params: LeaveActionParams) {
  return request.put<ListResult>({
    url: `/leave/reject?leaveCode=${params.leaveCode}&reject=${params.reject}&taskId=${params.taskId}`
  })
}

// 撤回
export function revocationLeave(params: LeaveActionParams) {
  return request.put<ListResult>({
    url: `/leave/revocation?leaveCode=${params.leaveCode}&flowStatus=${params.flowStatus}&taskId=${params.taskId}`
  })
}

// 撤销
export function cancelLeave(params: LeaveActionParams) {
  return request.put<ListResult>({
    url: `/leave/cancel?leaveCode=${params.leaveCode}&taskId=${params.taskId}`
  })
}

// 请假分页查询
export function getLeavePage(params: LeaveQueryParams) {
  return request.post<LeaveResult>({
    url: '/leave/selectByPage',
    data: params
  })
}
