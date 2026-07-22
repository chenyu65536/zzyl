import { request } from '@/utils/request'
import type {
  ListResult,
  ListModel,
  FormLevel,
  CardListResult,
  RefundList,
  RefundListModel,
  ContractListResult,
  ContractListModel,
  TypesListResult,
  TypesListModel
} from '@/api/model/liveInModel'
import type { ProjecListModel, PlanListModel, ListArrangeResult } from '@/api/model/serveModel'
import type { FormFloor } from '@/api/model/liveInModel'

// ========== 类型定义 ==========

// 账单支付参数
export interface FormPayParams {
  billId: number
  payType?: number
}

// 合同分页参数
export interface ContractListParams {
  pageNum?: number
  pageSize?: number
  contractNo?: string
  elderName?: string
  status?: number
  startTime?: number
  endTime?: number
}

// 预约参数
export interface ReservationParams {
  pageNum?: number
  pageSize?: number
  name?: string
  phone?: string
  status?: number
  type?: number
  startTime?: number
  endTime?: number
}

// 订单创建参数
export interface FormOrderParams {
  elderId: number
  projectId: number
  projectName: string
  price: number
  count: number
  mark?: string
  memberId?: number
}

// 退款参数
export interface RefundParams {
  productOrderNo: number
  operTionRefund: number
  tradingChannel: string
}

// 客户登录参数
export interface CustomerLoginParams {
  code: string
  openId?: string
}

// ========== 客户账单 ==========

// 支付账单
export function customerBillPay(params: FormPayParams) {
  return request.put<ListResult>({
    url: '/customer/bill',
    data: params
  })
}

// 账单详情
export function customerBillDetail(id: number) {
  return request.get<ListModel>({
    url: `/customer/bill/${id}`
  })
}

// 账单分页
export function customerBillPage(params: {
  transactionStatus?: number
  elderId?: number
  pageNum?: number
  pageSize?: number
}) {
  return request.get<ListResult>({
    url: '/customer/bill/page/',
    params
  })
}

// ========== 客户合同 ==========

// 合同分页
export function customerContractList(params: ContractListParams) {
  return request.get<ContractListResult>({
    url: '/customer/contract/list',
    params
  })
}

// ========== 客户预约 ==========

// 时间段预约统计
export function customerReservationCount(time?: number) {
  return request.get<ListResult>({
    url: '/customer/reservation/countByTime',
    params: time ? { time } : {}
  })
}

// 取消预约统计
export function customerReservationCancelledCount() {
  return request.get<ListResult>({
    url: '/customer/reservation/cancelled-count'
  })
}

// 新增预约
export function customerReservationAdd(params: FormApply) {
  return request.post<ListResult>({
    url: '/customer/reservation',
    data: params
  })
}

// 查询所有预约
export function customerReservationFindAll(mobile?: string, time?: number) {
  return request.get<ListResult>({
    url: '/customer/reservation',
    params: { mobile, time }
  })
}

// 预约分页
export function customerReservationPage(params: ReservationParams) {
  return request.get<ContractListResult>({
    url: '/customer/reservation/page',
    params
  })
}

// 取消预约
export function customerReservationCancel(id: number) {
  return request.put<ListResult>({
    url: `/customer/reservation/${id}/cancel`
  })
}

// ========== 客户房型 ==========

// 根据状态查询房型
export function customerRoomTypeList(status: number) {
  return request.get<TypesListResult>({
    url: `/customer/roomTypes?status=${status}`
  })
}

// ========== 客户订单 ==========

// 下单
export function customerCreateOrder(params: FormOrderParams) {
  return request.post<ListModel>({
    url: '/customer/orders',
    data: params
  })
}

// 下单参数检查
export function customerCreateOrderCheck(params: FormOrderParams) {
  return request.post<ListModel>({
    url: '/customer/orders/check',
    data: params
  })
}

// 取消订单
export function customerCancelOrder(orderId: number, reason: string) {
  return request.post<ListModel>({
    url: `/customer/orders/${orderId}/cancel?reason=${reason}`
  })
}

// 执行订单
export function customerDoOrder(orderId: number) {
  return request.post<ListModel>({
    url: `/customer/orders/${orderId}/do`
  })
}

// 删除订单
export function customerDeleteOrder(orderId: number) {
  return request.delete<ListModel>({
    url: `/customer/orders/${orderId}`
  })
}

// 订单详情
export function customerOrderDetail(orderId: number) {
  return request.get<ListModel>({
    url: `/customer/orders/${orderId}`
  })
}

// 订单分页
export function customerOrderPage(params: {
  status?: number
  orderNo?: string
  elderlyName?: string
  creator?: string
  startTime?: number
  endTime?: number
  pageNum: number
  pageSize: number
}) {
  return request.get<ListResult>({
    url: '/customer/orders/order/page',
    params
  })
}

// 护理项目详情
export function customerProjectDetail(id: number) {
  return request.get<ProjecListModel>({
    url: `/customer/orders/project/${id}`
  })
}

// 护理项目分页
export function customerProjectPage(params: {
  name?: string
  status?: number
  pageNum?: number
  pageSize?: number
}) {
  return request.get<ProjecListModel>({
    url: '/customer/orders/project/page',
    params
  })
}

// 申请退款
export function customerRefund(params: RefundParams) {
  return request.post<ListResult>({
    url: '/customer/orders/refund',
    data: params
  })
}

// ========== 客户用户 ==========

// 客户登录（微信）
export function customerLogin(params: CustomerLoginParams) {
  return request.post<ListResult>({
    url: '/customer/user/login',
    data: params
  })
}

// 客户设备数据分页
export function customerDeviceDataPage(params: {
  pageNum: number
  pageSize: number
  deviceName?: string
  accessLocation?: string
  locationType?: number
  functionId?: string
  startTime?: number
  endTime?: number
  status?: number
}) {
  return request.get<ListResult>({
    url: '/customer/user/get-page',
    params
  })
}

// 客户设备状态查询
export function customerDevicePropertyStatus(params: any) {
  return request.post<ListResult>({
    url: '/customer/user/QueryDevicePropertyStatus',
    data: params
  })
}

// 客户设备周数据分页
export function customerDeviceWeekDataPage(params: {
  pageNum: number
  pageSize: number
  deviceName?: string
  accessLocation?: string
  locationType?: number
  functionId?: string
  startTime?: number
  endTime?: number
  status?: number
}) {
  return request.get<ListResult>({
    url: '/customer/user/get-week-page',
    params
  })
}

// ========== 客户老人关联 ==========

// 新增关联
export function customerMemberElderAdd(params: any) {
  return request.post<ListResult>({
    url: '/customer/memberElder/add',
    data: params
  })
}

// 更新关联
export function customerMemberElderUpdate(params: any) {
  return request.post<ListResult>({
    url: '/customer/memberElder/update',
    data: params
  })
}

// 删除关联
export function customerMemberElderDelete(id: number) {
  return request.delete<ListResult>({
    url: `/customer/memberElder/deleteById/${id}`
  })
}

// 查询关联
export function customerMemberElderGet(id: number) {
  return request.get<ListResult>({
    url: `/customer/memberElder/getById`,
    params: { id }
  })
}

// 我的家人
export function customerMemberElderMy() {
  return request.get<ListResult>({
    url: '/customer/memberElder/my'
  })
}

// 分页查询关联
export function customerMemberElderList(params: {
  memberId?: number
  elderId?: number
  pageNum?: number
  pageSize?: number
}) {
  return request.get<ListResult>({
    url: '/customer/memberElder/list-by-page',
    params
  })
}
