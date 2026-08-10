export default {
  // 修改点：关闭直连外部 host，改为走 Vite 代理（/api -> http://localhost:8080）。
  // 原 isRequestProxy:true 会把 host 拼到请求 URL，直接访问 zhyl-admin-t.itheima.net，
  // 绕过本地后端且连接被远端关闭，导致登录失败。
  isRequestProxy: false,
  development: {
    // 开发环境接口请求
    host: '',
    // 开发环境 cdn 路径
    cdn: ''
  },
  test: {
    // 测试环境接口地址
    host: '',
    // 测试环境 cdn 路径
    cdn: ''
  },
  release: {
    // 正式环境接口地址
    host: '',
    // 正式环境 cdn 路径
    cdn: ''
  }
}
