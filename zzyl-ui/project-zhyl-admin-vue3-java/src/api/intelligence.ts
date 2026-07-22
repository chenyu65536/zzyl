import { request } from '@/utils/request'

// 获取所有产品列表
export function getProductList(params) {
  return request.post({
    url: '/iot/QueryProductList',
    data: params
  })
}
// 获取设备列表
export function getDeviceList(params) {
  return request.post({
    url: '/iot/QueryDevice',
    data: params
  })
}
// 添加设备
export function addDevice(params) {
  return request.post({
    url: '/iot/RegisterDevice',
    data: params
  })
}
// 编辑设备
export function editDevice(params) {
  return request.post({
    url: '/iot/UpdateDevice',
    data: params
  })
}
// 删除设备
export function deleteDevice(params) {
  return request.delete({
    url: '/iot/DeleteDevice',
    data: params
  })
}
// 获取设备详情
export function getDetail(params) {
  return request.post({
    url: '/iot/QueryDeviceDetail',
    data: params
  })
}
// 详情运行状态事件管理
export function getPublishedList(params) {
  return request.post({
    url: '/iot/QueryThingModelPublished',
    data: params
  })
}
// 详情运行状态状态的卡片
export function getPropertyStatusList(params) {
  return request.post({
    url: '/iot/QueryDevicePropertyStatus',
    data: params
  })
}
// 查看运行状态的历史数据
export function getPropertyDataList(params) {
  return request.post({
    url: '/iot/QueryDevicePropertyData',
    data: params
  })
}
// 详情事件管理的记录
export function getEventDataList(params) {
  return request.post({
    url: '/iot/QueryDeviceEventData',
    data: params
  })
}
// 报警规则
// 获取报警规则列表
export function getWarnList(params) {
  return request.get({
    url: '/alert-rule/get-page',
    params
  })
}
// 创建规则
export function createRule(params) {
  return request.post({
    url: '/alert-rule/create',
    data: params
  })
}
// 编辑规则
export function updateRule(params) {
  return request.put({
    url: `/alert-rule/update/${params.id}`,
    data: params
  })
}
// 删除规则
export function deleteRule(id) {
  return request.delete({
    url: `/alert-rule/delete/${id}`
  })
}
// 禁用启用
export function statusRule(params) {
  return request.put({
    url: `/alert-rule/${params.id}/status/${params.status}`
  })
}
// 规则详情
export function getRuleDetail(id) {
  return request.get({
    url: `/alert-rule/read/${id}`
  })
}
// 设备数据
export function getDeviceDataList(params) {
  return request.get({
    url: '/device-data/get-page',
    params
  })
}
// 处理报警
export function deviceUpdate(params) {
  return request.put({
    url: `/device-data/update/${params.id}`,
    data: params
  })
}
