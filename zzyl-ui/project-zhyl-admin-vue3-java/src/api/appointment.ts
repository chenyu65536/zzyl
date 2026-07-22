import { request } from '@/utils/request'
import type {
  formParams,
  ListResult,
  ListModel
} from '@/api/model/appointmentModel'

// ========== 预约管理 ==========

// 分页查询预约分页
export function getSubscribeList(params) {
  return request.get<ListResult>({
    url: `/reservation/page`,
    params
  })
}
// 查询所有预约
export function getSubscribeAll(params) {
  return request.get<ListResult>({
    url: `/reservation`,
    params
  })
}
// 根据ID查询预约
export function getSubscribeDetails(id: number) {
  return request.get<ListResult>({
    url: `/reservation/${id}`
  })
}
// 新增预约
export function subscribeAdd(params: ListModel) {
  return request.post<ListModel>({
    url: '/reservation',
    data: params
  })
}
// 更新预约
export function subscribeUpdate(params: ListModel) {
  return request.put<ListModel>({
    url: `/reservation/${params.id}`,
    data: params
  })
}
// 取消预约
export function subscribeCancel(id: number) {
  return request.put<ListResult>({
    url: `/reservation/${id}/cancel`
  })
}
// 删除预约
export function subscribeDelete(id: number) {
  return request.delete({
    url: `/reservation/${id}`
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
