---
name: feature-status
description: 罗列 zzyl 养老系统已有功能清单,以及相比《养老系统功能清单【MVP】20260730.xlsx》仍缺失的部分。用户询问"功能清单/功能现状/还缺什么/MVP对比/迁移进度"时使用。
---

# 养老系统功能现状(以 MVP 功能清单 Excel 为基准)

基准文件:`C:\Users\Administrator\Desktop\养老系统功能清单【MVP】 20260730.xlsx`
盘点时间:2026-08-11(物资/餐饮/档案/楼栋员工/看板迁移完成后)。

## 接口文档(API 文档)

后端启动后由 Knife4j 自动生成在线接口文档:

| 端口 | 说明 | 地址 |
|---|---|---|
| 本地开发(9995) | IDEA 直接启动 `ZzylApplication` | http://localhost:9995/doc.html |
| Docker 部署(8080) | `docker compose up` 后访问 | http://localhost:8080/doc.html |
| Swagger 原生 UI | 通用 Swagger 页面 | http://localhost:9995/swagger-ui.html |
| OpenAPI JSON | 供导入 Postman / Apifox | http://localhost:9995/v2/api-docs |

回答用户时:直接输出下面两节内容(可按用户关注点裁剪);若怀疑代码已变化,先按"刷新方法"一节校验再输出。

## 一、已有功能(按 Excel 清单逐项对照)

### 首页展示
| Excel 功能 | 状态 | 实现位置 |
|---|---|---|
| 功能快捷入口(按权限展示) | ✅ 后端已有 | zzyl-security 资源菜单 `/resource/menus`、`/resource/myButten` |
| 机构看板(床位/房间/入住老人/用餐/起居/健康标识/IOT设备) | ✅ 已有(本次新增) | `HomeController` `/home/overview` + `DashboardMapper` |

### 入住管理
| Excel 功能 | 状态 | 实现位置 |
|---|---|---|
| 合同管理(台账/续签变更终止/到期提醒/归档) | ✅ 已有 | `ContractController` `/contract`,`ContractJob` 状态刷新 |
| 床位分配 | ✅ 已有 | `BedController` `/bed`,入住流程绑定床位 |
| 入住办理(申请/评估/床位分配/登记) | ✅ 已有 | `CheckInController` `/checkIn`,Activiti 工作流(check_in.bpmn) |
| 离院管理(申请/结算/登记/回访) | ✅ 已有 | `RetreatController`、`RetreatBillController`,retreat.bpmn |

### 运营管理
| Excel 功能 | 状态 | 实现位置 |
|---|---|---|
| 物品管理(入库/出库/库存预警/盘点) | ✅ 已有(本次迁移) | `/warehouse` `/material` `/warehouse-record` `/outbound-record` `/inventory`(审核流+批次库存+低库存预警) |
| 护理工作管理(计划/记录/质检) | ✅ 已有 | `NursingController` 等 `/nursing*`(计划/项目/等级/任务执行打卡) |
| 餐饮管理(食谱/订餐/食材采购/用餐记录) | ✅ 已有(本次迁移) | `/dishes` `/catering-set` `/meal-order`(套餐计价/送餐/用餐打卡);食材采购复用物资出入库 |

### 费用管理
| Excel 功能 | 状态 | 实现位置 |
|---|---|---|
| 费用设置(费用项目/月标准/初始费用/优惠) | ✅ 基本覆盖 | 价格分布在 `RoomType`、`NursingLevel`、`NursingProject`、`CheckInConfig`(无独立"费用项目设置"页,见缺口⑤) |
| 费用预缴 | ✅ 已有 | `Balance` 余额 + `PrepaidRechargeRecord` `/bill/prepaidRechargeRecord` |
| 费用结算(缴费/账单/调整/预警/退住结算/报表) | ✅ 大部分已有 | `BillController` `/bill`(月度账单Job/欠费查询/退住结算/退款凭证);月费用报表未见独立接口 |

### 订单管理
| Excel 功能 | 状态 | 实现位置 |
|---|---|---|
| 线上订单(后台创建:餐饮/药品/增值服务) | ✅ 已有 | 服务订单 `OrderController` `/orders` + 餐饮订单 `/meal-order`(药品类可走增值服务) |
| 订单汇总(状态/时间/编号/客户筛选) | ✅ 已有 | `/orders/search` 分页筛选 |
| 订单支付(微信扫码+线下收款) | ⚠️ 部分已有 | zzyl-pay 微信支付 v3(JSAPI/H5/退款);线下支付记录 `/bill/payRecord`;支付宝与聚合支付见缺口① |
| 账单汇总 | ✅ 已有 | `/bill/page` 多条件筛选 |

### 老年人管理
| Excel 功能 | 状态 | 实现位置 |
|---|---|---|
| 档案管理(基础/健康/生活档案/变更记录) | ✅ 已有(本次迁移) | `ElderRecordController` `/elder-record`(健康档案手工录入+拍照可传OSS图片URL,变更自动记日志,作废/恢复) |

### 系统管理
| Excel 功能 | 状态 | 实现位置 |
|---|---|---|
| 角色/账号管理 | ✅ 已有 | zzyl-security `RoleController` `/role`、`UserController` `/user`(含停用/重置密码) |
| 操作日志 | ❌ 缺失 | 见缺口② |
| 机构基本信息维护 | ❌ 缺失 | 见缺口③ |
| 楼栋/楼层/房间/床位管理 | ✅ 已有(楼栋为本次迁移) | `/building` `/floor` `/room` `/bed`(床位状态空闲/占用) |
| 收费项目配置 | ✅ 基本覆盖 | 同费用设置 |
| IOT设备对接(电子工牌API) | ✅ 已有 | `DeviceController` `/iot`(阿里云 IoT SDK 全套设备管理)+ `DeviceData` 告警数据 + AMQP 订阅 + WebSocket 推送,电子工牌走同一接入通道 |

## 二、相比 Excel 仍缺失的部分

1. **支付宝支付 / 聚合支付正向扫码**(Excel 订单支付 + 备注列):现仅微信支付。Excel 备注要求"对接聚合支付智能POS 或 动态收款码回调,二选一"——两者均未实现;线下扫码记录流水号/凭证图片已可通过 `/bill/payRecord` + OSS 上传覆盖。
2. **操作日志**(系统基础配置):无 sys_log 表/切面/查询接口,关键操作不可追溯。参考项目 retirementManagePublic 也没有,需自研(建议 AOP + 注解)。
3. **机构基本信息维护**(机构信息管理):机构名称/地址/联系方式/床位数量等无维护实体与页面(Dept 是组织架构,不是机构档案)。
4. **月费用报表**(费用结算子项):账单数据齐全但无报表聚合导出接口。
5. **独立的"费用项目设置"模块**(费用设置子项):收费标准分散在房型/护理等级/服务项目中,无统一费用项目 CRUD;是否需要合并取决于产品决策。
6. **新迁移模块的前端页面与菜单数据**:物资/餐饮/档案/楼栋/员工/看板仅后端 API 完成;zzyl-ui 无对应页面,`resource` 菜单表无对应条目(种子参照 `.workbuddy/seed_role_menu.sql`)。
7. **数据库执行**:`zzyl_ddl.sql` 末尾"MVP 功能迁移新增表"段(21 张表 + floor 加 building_id)尚需在 PostgreSQL 手工执行。
8. **健康档案 OCR**:Excel 备注明确"本期 MVP 拍照上传/手工录入,二期 OCR"——按期不做,列此备查。

## 刷新方法(怀疑过期时执行)

1. 列控制器确认模块存在:`ls zzyl-web/src/main/java/com/zzyl/controller/ zzyl-security/src/main/java/com/zzyl/controller/`
2. 查缺口是否已补:`grep -ril "alipay\|操作日志\|OperLog\|机构信息" --include="*.java" zzyl-*/src`(命中则更新第二节)
3. 查 DDL 是否已执行/变更:对比 `zzyl_ddl.sql` 中 `CREATE TABLE` 数量(当前 65)与实际库
4. Excel 基准若更新,用 Node 解压读取 sharedStrings.xml 重新对照(本机无 Python)
