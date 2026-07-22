import { request } from '@/utils/request'
import type {
  formParams,
  ListResult,
  ListModel
} from '@/api/model/appointmentModel'

// ========== 老人管理 ==========

// 分页查询分页
export function getOldManList(params) {
  return request.get<ListResult>({
    url: `/elder/selectList`,
    params
  })
}
// 分页查询分页
export function getSelectListByPage(params) {
  return request.get<ListResult>({
    url: `/elder/selectListByPage`,
    params
  })
}
// 设置护理员
export function setNursingForOlder(params: any) {
  return request.put({
    url: `/elder/setNursing`,
    data: params
  })
}
// 校验身份证号
export function getIsCard(params) {
  return request.get({
    url: `/elder/selectByIdCard`,
    params
  })
}

// ========== 来访管理 ==========

// 分页查询来访分页
export function getVisitList(params) {
  return request.get<ListResult>({
    url: `/visit/page`,
    params
  })
}
// 查询所有来访
export function getVisitAll(params) {
  return request.get<ListResult>({
    url: `/visit`,
    params
  })
}
// 根据ID查询来访
export function getVisitDetails(id: number) {
  return request.get<ListModel>({
    url: `/visit/${id}`
  })
}
// 到访时间
export function visitTime(params) {
  return request.put<formParams>({
    url: `/reservation/${params.id}/visit?time=${params.time}`
  })
}
// 来访添加
export function visitAdd(params: ListModel) {
  return request.post<ListModel>({
    url: '/visit',
    data: params
  })
}
// 来访编辑
export function visitUpdate(params: ListModel) {
  return request.put<ListModel>({
    url: `/visit/${params.id}`,
    data: params
  })
}
// 取消来访
export function visitCancel(id: number) {
  return request.put<ListModel>({
    url: `/visit/${id}/cancel`
  })
}
// 删除来访
export function visitDelete(id: number) {
  return request.delete({
    url: `/visit/${id}`
  })
}

// ========== 预约管理 ==========

// 分页查询预约分页
export function getSubscribeList(params) {
  return request.get<ListResult>({
    url: `/reservation/page`,
    params
  })
}
// 查询所有预约
export function getReservationAll(params) {
  return request.get<ListResult>({
    url: `/reservation`,
    params
  })
}
// 根据ID查询预约
export function getReservationDetails(id: number) {
  return request.get<ListResult>({
    url: `/reservation/${id}`
  })
}
// 新增预约
export function reservationAdd(params: ListModel) {
  return request.post<ListModel>({
    url: '/reservation',
    data: params
  })
}
// 更新预约
export function reservationUpdate(params: ListModel) {
  return request.put<ListModel>({
    url: `/reservation/${params.id}`,
    data: params
  })
}
// 取消预约
export function reservationCancel(id: number) {
  return request.put<ListModel>({
    url: `/reservation/${id}/cancel`
  })
}
// 删除预约
export function reservationDelete(id: number) {
  return request.delete({
    url: `/reservation/${id}`
  })
}

// ========== 分布式表单 ==========

// 校验身份证号
export function validateIdCard(params) {
  return request.post<ListResult>({
    url: '/validate-password',
    data: params
  })
}

// ========== 项目详情 ==========

// 获取项目详情列表
export function getProjectDetailList(params) {
  return request.get<ListResult>({
    url: '/get-detail-list',
    params
  })
}
