
// 测试环境
// 修改点：原硬编码 localhost，真机/生产无法访问。改为允许通过本地存储 'ZZYL_BASE_URL' 覆盖，
// 部署时注入服务器地址即可，无需改代码。同时需在小程序后台配置 request 合法域名（https）。
let baseUrlValue = 'http://localhost:9995/customer' //测试
try {
  const override = uni.getStorageSync('ZZYL_BASE_URL')
  if (override) baseUrlValue = override
} catch (e) {
  // 忽略读取异常，使用默认值
}
export const baseUrl = baseUrlValue

//不需要跳转到登录页面的接口
export const notToLoginApiUrl = [
]