# 变更记录（CHANGELOG）

> 格式：日期 | 模块 | 更新内容 | 开发者

2026-07-22 | 文档 | 优化美化 README.md：修正技术栈版本徽章（Activiti 7.10.0、MyBatis 2.2.0 原误标为 MyBatis Plus）、新增目录导航、环境要求、部署指南、安全说明、许可证与鸣谢章节，统一排版并保留原有图片引用 | itxinfei

2026-07-23 | 公共/安全/调度/消息 | 修复：①雪花算法序列随机化(Math.random)导致ID碰撞，改为同毫秒自增；②线程池拒绝策略DiscardPolicy改为CallerRunsPolicy，避免消息静默丢弃；③全局异常处理器移除7处printStackTrace，未知异常不再将完整堆栈作为msg返回客户端；④用户创建/重置密码前校验默认密码非空，避免哈希字符串"null"；⑤主类启用@EnableScheduling恢复死任务，并移除tradingJob上冗余@Scheduled避免与xxl-job双触发；⑥AMQP改为CLIENT_ACKNOWLEDGE手动确认+启动容错+@PreDestroy关闭连接+clientId追加PID避免多实例冲突 | AI

2026-07-23 | 安全/支付/跨域 | 修复（第二批高危）：①越权改密：UserServiceImpl.modifyPasswords 不再信任前端传入的用户id，强制以 UserThreadLocal 当前登录用户为准，仅允许改自己密码，并校验新密码非空；②退款幂等失效：RefundRecordServiceImpl.findRefundRecordByProductOrderNoAndSending 原恒返回null导致防重复退款校验失效，现复用 selectListByProductOrderNo 过滤 SENDING 状态记录；③DataScopeAspect：deptNo 拼接进 ${dataScope} 前增加纯数字白名单校验阻断二次SQL注入，System.out.println 改为 SLF4J 日志；④CorsConfig：原为 WebFlux 响应式 CorsWebFilter 在 Servlet MVC 中不生效的死配置，重写为 Servlet 版 CorsFilter，允许来源改为配置项 zzyl.cors.allowed-origins（默认*兼容开发，生产应配具体域名）| AI

2026-07-23 | 安全/前端 | 修复（第三批：前端+认证纵深）：①UserAuth.isEnabled() 原恒返回 true，被停用(data_state=1)账号仍可绕过状态校验，现改为按 UserVo.dataState 判定（防御纵深，登录SQL已用 data_state='0' 过滤）；②Login.vue 自动登录原把明文密码写入 localStorage，现仅缓存用户名+勾选状态，杜绝凭据泄露；③vite.config.ts prodEnabled 由 true 改为 false，杜绝生产环境 mock 拦截/伪造后端接口；④ECharts 内存泄漏：MiddleChart(切换tab前dispose+卸载dispose，并修正 serveMonitorChart 误用 enterContainer 的容器bug)、OutputOverview(卸载dispose)、TopPanel(5图统一数组持有+卸载dispose) | AI

2026-07-23 | 构建/仓库卫生 | 完善（第四批）：①vite.config.ts 新增 build.manualChunks，将 echarts 单独拆包、其余第三方归入 vendor，解决单 chunk 约1.69MB 首屏加载慢、无法缓存问题；②根 .gitignore 补充 uni_modules/（uniapp 依赖缓存，等同 node_modules，此前遗漏导致 1.3万文件入库，违反"禁止提交缓存"规则）；③全量扫描所有 mapper XML 的 ${}，确认仅 dataScope 机制使用（RetreatMapper/LeaveMapper/PostMapper），片段由 DataScopeAspect 服务端拼装且 deptNo 已白名单校验，无其他 SQL 注入点（安全审计闭环）| AI

2026-07-23 | 安全/前端 | 修复（第五批·最终批）：①默认口令强制改密：UserVo/UserAuth 新增 needResetPwd 字段，LoginServiceImpl 登录时若明文口令仍为系统默认口令(securityConfigProperties.getDefaulePassword())则标记 needResetPwd=true；前端 Login.vue 提示"请尽快修改密码"，Header.vue 挂载后自动弹出改密弹窗(PaddWord)，杜绝长期使用弱初始口令；②token 前端过期兜底：user.ts 登录时记录 tokenExpireAt(3天，与后端 JWT/Redis TTL 对齐)，request/index.ts 拦截器校验过期则清理本地登录态并跳转登录；③清除 token 明文打印：uniapp 工程(pages/login/index.vue、Appointment.vue)与 mp-weixin 编译产物(Appointment.js)移除 console.log(token)，避免 token 泄露至开发者工具/真机控制台；④小程序 env.js 硬编码 localhost 改为允许本地存储 ZZYL_BASE_URL 覆盖，真机/生产可注入服务器地址（且需在小程序后台配 request 合法域名）；⑤.gitignore 补充 mp-weixin/ 排除小程序编译产物 | AI

2026-07-23 | 前端/代码卫生 | 修复（第七批）：批量清理前端调试 console——admin 工程与 uniapp 工程中活跃的 console.log/info/debug 调试语句全部移除（单行闭合的脚本整行删除，DialogFormExecute.vue 唯一的多行调用手动精确删除整块），保留 console.error/warn 错误边界日志；注释中的 // console.log 死代码一并清理（仅删整行即注释的行，避免误删行内代码）；最终仅剩 3 处嵌套注释 // // console.log（死代码无害保留）。涉及约 74 个前端文件，lint 抽样 0 错误，无语法破坏。另复查"moment+dayjs 并存"告警，经核实代码已无 from 'moment' 直接引用（仅 dayjs 在 8 处使用），该并存问题实际不存在 | AI

2026-07-23 | 安全/上传/日志/前端 | 修复（第八批·审查查漏补缺）：①文件上传安全加固：CommonController.upload 增加空文件校验、10MB 大小上限、真实图片内容校验(ImageIO)、扩展名白名单(jpg/jpeg/png/gif/bmp，排除 svg 防存储型 XSS)、文件名健壮性(无扩展名不再抛 500)、store 异常捕获；保留 /common/** 匿名放行(前端 t-upload 直传不带 token，移除会致 401)；②RetreatServiceImpl 月度账单生成 catch 由 e.printStackTrace() 静默吞异常改为 SLF4J log.error 并保留上下文(elderId/billMonth)；③生产代码残留 printStackTrace/System.out 统一替换为 SLF4J 日志(WebSocketServer/OrderJob/BillJob/AmqpClient/ActFlowCommServiceImpl)，RetryServiceImpl 显式声明 Logger 规避 Lombok 解析环境差异；④EaseRequest.vue 调试工具 formatJson 对每行 JSON 文本做 HTML 转义，消除 v-html 渲染 API 响应时的存储型 XSS；⑤fastjson 升级 1.2.75→1.2.83 安全版本(同包名最小改动，修复已知 CVE) | AI
