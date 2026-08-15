# ============================================================
# zzyl 前端镜像 - 多阶段构建
# Vue3 + Vite + TDesign，产物由 Nginx 托管
# ============================================================

# ---------- 阶段 1：Node 构建 ----------
FROM node:18-alpine AS builder
WORKDIR /app

# 使用项目自带的 .npmrc（node-linker=hoisted）
COPY package.json package-lock.json ./
RUN npm install --registry=https://registry.npmmirror.com

# 复制源码并构建（release 模式：走 /api 代理，host 为空）
COPY . .
RUN npm run build

# ---------- 阶段 2：Nginx 运行 ----------
FROM nginx:1.25-alpine
LABEL maintainer="信息中心研发组 <user@itcast.cn>"

# 复制构建产物
COPY --from=builder /app/dist/ /usr/share/nginx/html/

# 复制 Nginx 配置（/api 反向代理到后端容器）
COPY ./default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]