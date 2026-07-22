<div align="center">

<img src="docs/images/logo.png" alt="中州养老院智慧管理系统" width="140"/>

# 中州养老院智慧管理系统

<h3 style="color:#722ed1; font-weight:normal; margin-top:8px">科技赋能养老 · 专业成就品质</h3>

<p align="center" style="color:#595959; max-width:720px; line-height:1.8">
基于 Spring Boot + Vue 3 的智慧养老综合解决方案，整合入住管理、服务记录、财务管理、<br/>
请假审批、家属交互等核心模块，助力养老机构数字化转型。
</p>

<!-- 技术栈徽章 -->
<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-11-orange"/>
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring_Boot-2.7.4-brightgreen"/>
  <img alt="Spring Security" src="https://img.shields.io/badge/Spring_Security-5.7.3-red"/>
  <img alt="MySQL" src="https://img.shields.io/badge/MySQL-8.0.19-blue"/>
  <img alt="MyBatis" src="https://img.shields.io/badge/MyBatis-2.2.0-green"/>
  <img alt="PageHelper" src="https://img.shields.io/badge/PageHelper-1.3.0-green"/>
  <img alt="Druid" src="https://img.shields.io/badge/Druid-1.2.1-blueviolet"/>
  <img alt="Redis" src="https://img.shields.io/badge/Redis-6.x-red"/>
  <img alt="Redisson" src="https://img.shields.io/badge/Redisson-3.11.2-red"/>
  <img alt="Activiti" src="https://img.shields.io/badge/Activiti-7.10.0-orange"/>
  <img alt="JWT" src="https://img.shields.io/badge/JWT-3.8.1-lightgrey"/>
  <img alt="Lombok" src="https://img.shields.io/badge/Lombok-1.18.22-yellow"/>
  <img alt="Hutool" src="https://img.shields.io/badge/Hutool-5.8.0.M3-green"/>
  <img alt="Knife4j" src="https://img.shields.io/badge/Knife4j-3.0.3-green"/>
  <img alt="XXL Job" src="https://img.shields.io/badge/XXL_Job-2.3.0-green"/>
  <img alt="Aliyun OSS" src="https://img.shields.io/badge/Aliyun_OSS-3.10.2-orange"/>
  <img alt="Aliyun IoT" src="https://img.shields.io/badge/Aliyun_IoT-AMQP-blue"/>
  <img alt="WeChat Pay" src="https://img.shields.io/badge/WeChat_Pay-0.4.7-green"/>
</p>

</div>

---

## 📑 目录

- [项目简介](#项目简介)
- [核心特性](#核心特性)
- [技术栈](#技术栈)
- [环境要求](#环境要求)
- [快速开始](#快速开始)
- [部署指南](#部署指南)
- [项目结构](#项目结构)
- [开发与管理](#开发与管理)
- [文档规范](#文档规范)
- [护理功能开发](#护理功能开发)
- [安全说明](#安全说明)
- [许可证](#许可证)
- [鸣谢](#鸣谢)

---

## 项目简介

中州养老院是一家专业养老服务机构，秉持"以人为本、关爱生命"的理念，为长者提供高品质养老服务。院区占地面积超 30,000 平方米，总建筑面积 40,000 平方米，现有床位 800 余张，员工 200 余人，先后荣获"全国优秀养老院"等多项荣誉。

### 行业背景

中国老龄化程度持续加深，智慧养老成为行业必然趋势。2022 年中国养老产业市场规模达 **10.3 万亿元**，预计 2027 年将突破 **21.1 万亿元**。

<p align="center">
  <img src="docs/images/image-20230808134224075.png" alt="市场规模" width="600"/>
</p>

### 整体业务流程

覆盖从来访参观到退住办理的全生命周期，包括来访管理、入退管理、在住管理、服务管理、财务管理等核心模块。

<p align="center">
  <img src="docs/images/image-20230722161536290.png" alt="业务流程" width="600"/>
</p>

### 系统架构

> 原型地址：https://rp-java.itheima.net/zhyl/

项目分为两大终端：

- **管理后台**：面向员工，提供入住/退住、服务记录、财务管理等功能
- **家属端**：面向家属，支持查看老人信息、在线缴费、服务下单

<p align="center">
  <img src="docs/images/中州-架构图@2x.png" alt="系统架构" width="600"/>
</p>

### 技术架构

<p align="center">
  <img src="docs/images/中州-技术架构图.png" alt="技术架构" width="600"/>
</p>

---

## 核心特性

| 维度 | 亮点 |
|------|------|
| 🏆 管理效率 | 业务流程数字化，智能排班节省约 30% 人力成本，实时数据看板辅助决策 |
| 💡 服务质量 | 健康数据动态监测，护理服务标准化，家属端实时互动 |
| 📈 运营效益 | 财务自动统计，资源利用率提升约 40%，风险预警保障安全 |

---

## 技术栈

### 后端

| 分类 | 技术 |
|------|------|
| 核心框架 | Spring Boot 2.7.4、Spring Security 5.7.3 |
| 持久层 | MyBatis 2.2.0、PageHelper 1.3.0、Druid 1.2.1 |
| 缓存 / 分布式 | Redis 6.x、Redisson 3.11.2 |
| 工作流 | Activiti 7.10.0 |
| 安全 / 工具 | JWT 3.8.1、Lombok 1.18.22、Hutool 5.8.0.M3、Knife4j 3.0.3、Orika 1.5.4、ZXing 3.3.3、Kaptcha 2.3.2、OSHI 5.6.0 |
| 任务调度 | XXL-JOB 2.3.0 |
| 云服务 | 阿里云 OSS 3.10.2、阿里云 IoT（AMQP）、微信支付 0.4.7 |

### 前端

| 分类 | 技术 |
|------|------|
| 管理后台 | Vue 3 + TypeScript + Vite + TDesign |
| 移动端 | 微信小程序 / UniApp（家属端与小程序同源） |

---

## 环境要求

| 角色 | 技术项 | 要求 |
|------|--------|------|
| 后端 | JDK | **11+**（activiti 7.10.0 依赖 Java 11 字节码，不支持 JDK 8） |
| 后端 | Maven | 3.6+（需可访问 activiti-releases 专用仓库） |
| 后端 | 数据库 | MySQL 5.7+ |
| 后端 | 缓存 | Redis 6.x |
| 前端 | Node.js | v16.20.0 |
| 前端 | 框架 | Vue 3 + TypeScript |
| 前端 | 组件库 | TDesign |

> 完整的服务器环境准备、数据库初始化、Docker 部署步骤见仓库根目录 **[部署文档.md](./部署文档.md)**。

---

## 快速开始

本项目前后端同仓：后端为 Maven 多模块工程，前端位于 `zzyl-ui/` 目录。

### 1. 获取代码

```bash
git clone https://gitee.com/itxinfei/zzyl.git
cd zzyl
```

### 2. 配置后端

仓库**不提交**含密钥的真实配置文件，仅提供脱敏模板：

```bash
# 复制模板并填入你自己的密钥 / 数据库连接信息
cp zzyl-web/src/main/resources/application-example.yml \
   zzyl-web/src/main/resources/application.yml
```

打开 `application.yml`，按需修改以下项（推荐用环境变量注入，避免明文）：

- `spring.datasource`：MySQL 地址、账号、密码
- `spring.redis`：Redis 地址、密码
- `zzyl.framework.jwt.base64-encoded-secret-key`：JWT 签名密钥
- `zzyl.framework.oss` / `zzyl.aliyun`：阿里云 OSS、IoT 密钥
- `zzyl.wechat`：微信小程序 appId / appSecret

### 3. 启动后端

```bash
# 使用 JDK 11 编译运行（默认 JAVA_HOME 可能为 JDK 8，需先切换）
export JAVA_HOME="/path/to/jdk-11"
mvn clean package -DskipTests
java -jar zzyl-web/target/zzyl-web-1.0.1.jar
```

后端默认端口 `9995`，API 文档（Knife4j）地址：`http://localhost:9995/doc.html`。

### 4. 启动前端

```bash
cd zzyl-ui
npm install
npm run dev      # 开发模式
# npm run build  # 生产构建，产物输出到 dist/
```

---

## 部署指南

生产环境部署（服务器环境准备、MySQL 建库、Redis、xxl-job-admin、Docker 编排、Nginx 反向代理、小程序发布）请参考仓库根目录 **[部署文档.md](./部署文档.md)**，其中包含：

- 服务器环境准备与依赖清单
- MySQL 建库（`zhyl-auth`，注意库名含连字符需反引号）
- Redis / xxl-job-admin（Docker）搭建
- 后端 Dockerfile 与 docker-compose 示例
- 前端 Nginx 配置示例
- 常见问题 FAQ

---

## 项目结构

### 工程结构

```
zzyl/
├── zzyl-common     # 通用模块：统一异常、工具类、常量、基类
├── zzyl-framework  # 框架核心：配置类、拦截器、第三方集成
├── zzyl-pay        # 支付组件：微信扫码支付
├── zzyl-security   # 安全组件：权限认证、JWT
├── zzyl-service    # 业务层：核心业务逻辑
├── zzyl-web        # 控制层：RESTful 接口、启动入口
└── zzyl-ui         # 前端：管理后台（Vue3）与小程序/UniApp 源码
```

> `common`、`framework`、`service`、`web` 是日常开发最常接触的模块。

### 模块详解

#### zzyl-common（通用模块）

```
com.zzyl/base/
├── AjaxResult              # 统一接口响应
├── BaseDto / BaseEntity / BaseVo  # 各层基类
├── PageResponse            # 分页封装
├── exception/              # 异常体系
├── utils/                  # 工具类
└── vo/                     # 公共视图对象
```

> **DTO**：层间数据传输；**VO**：向前端返回展示数据

#### zzyl-framework（框架模块）

```
com.zzyl/
├── config/    # OSS、Swagger、MyBatis、WebMvc 配置
├── intercept/ # 自动填充字段拦截器
└── properties/# 配置文件属性读取
```

#### zzyl-service（业务模块）

```
com.zzyl/
├── dto/ entity/ enums/ mapper/ service/ vo/
└── resources/mapper/    # MyBatis XML 映射
```

#### zzyl-web（控制层）

```
com.zzyl/controller/     # RESTful 接口
└── ZzylApplication       # 启动入口
```

---

## 开发与管理

### 项目生命周期

需求分析 → 设计 → 编码 → 测试 → 部署 → 运维

<p align="center">
  <img src="docs/images/image-20230724164928484.png" alt="生命周期" width="600"/>
</p>

### 开发模式

| 模式 | 特点 | 适用场景 |
|------|------|----------|
| 瀑布模型 | 顺序式开发，阶段严格递进 | 需求明确、变更少 |
| 敏捷开发 | 迭代式开发，快速响应变化 | 需求多变、周期短 |
| DevOps | 开发运维融合，强调自动化持续交付 | 高频部署、快速迭代 |

<p align="center">
  <img src="docs/images/image-20230724164736889.png" alt="瀑布模型" width="500"/>
  <img src="docs/images/image-20230724164900781.png" alt="敏捷开发" width="500"/>
</p>

<p align="center">
  <img src="docs/images/image-20230725093037210.png" alt="DevOps" width="500"/>
  <img src="docs/images/image-20230729094020085.png" alt="三者对比" width="500"/>
</p>

### 代码规范要点

- 命名清晰，遵循驼峰法
- 缩进统一（4 空格）
- 注释描述意图，减少行内注释
- 方法职责单一，参数不超过 3 个
- 异常处理提供清晰的错误信息

---

## 文档规范

### 原型与 PRD

- **PRD**（Product Requirements Document）：产品需求文档，描述功能、场景和业务规则，是开发核心依据
- **原型**：交互原型图，直观展示页面布局和操作流程

### UI / UE 设计

- **UI**（用户界面）：负责视觉界面设计，是用户与系统的桥梁
- **UE**（用户体验）：关注产品易用性和使用感受

设计师核心职责：界面设计、交互设计、原型制作、视觉规范、可用性测试

### 个人开发计划

需求分析 → 编码自测 → 接口联调

---

## 护理功能开发

后端开发标准化流程：需求分析 → 评估工期 → 表结构设计 → 接口设计 → 编码实现 → 接口联调

> 设计阶段最耗时，包含需求分析、工期评估、表结构设计、接口设计。这几步扎实完成后，编码水到渠成。

---

## 安全说明

- 仓库**不提交**任何真实密钥。`zzyl-web/src/main/resources/application.yml` 已被 `.gitignore` 排除，仅 `application-example.yml` 入库作为脱敏模板。
- 克隆仓库后，请按「快速开始」步骤自行创建 `application.yml` 并填入你自己的密钥与数据库连接信息。
- **切勿**将含有真实密钥的配置文件提交到仓库或粘贴到 Issue / Pull Request 中。
- 历史上若密钥曾意外入库，请立即到对应云平台（阿里云 RAM、微信公众平台等）轮换，并清理 Git 历史后强推。

---

## 许可证

本项目基于 [Apache License 2.0](./LICENSE) 开源协议发布。

---

## 鸣谢

- 项目基础架构与业务逻辑参考自 **黑马程序员（itheima）** 智慧养老课程。
- 感谢 Spring 生态、Vue 生态及各类开源组件对本项目的支撑。

<p align="center" style="color:#bfbfbf; font-size:12px; margin-top:24px">
Made with ❤️ for smarter elderly care
</p>
