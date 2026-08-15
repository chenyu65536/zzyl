# ============================================================
# zzyl 后端镜像（仅运行时）
#
# 说明：
#   1. 本机网络无法直连 registry-1.docker.io，基础镜像走 daocloud 镜像源
#   2. 容器内不再跑 Maven（同样会被网络卡住），改为直接拷贝本地打好的 jar
#      构建镜像前先在宿主机执行：
#      "D:/Program Files (x86)/IntelliJ IDEA 2026.1/plugins/maven/lib/maven3/bin/mvn.cmd" -o package -pl zzyl-web -am -DskipTests
# ============================================================

FROM docker.m.daocloud.io/library/eclipse-temurin:17-jre
LABEL maintainer="zzyl"
WORKDIR /app

# 本地构建产物（.dockerignore 已放行 zzyl-web/target/zzyl-web.jar）
COPY zzyl-web/target/zzyl-web.jar app.jar

# 开放端口（后端 HTTP 端口）
EXPOSE 8080

# Redisson/FST 序列化需要打开相关 JDK 模块，否则启动报 InaccessibleObjectException
ENV JAVA_OPTS="--add-opens=java.base/java.lang=ALL-UNNAMED \
--add-opens=java.base/java.lang.reflect=ALL-UNNAMED \
--add-opens=java.base/java.util=ALL-UNNAMED \
--add-opens=java.base/java.util.concurrent=ALL-UNNAMED \
--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED \
--add-opens=java.base/java.util.concurrent.locks=ALL-UNNAMED \
--add-opens=java.base/java.io=ALL-UNNAMED \
--add-opens=java.base/java.net=ALL-UNNAMED \
--add-opens=java.base/java.nio=ALL-UNNAMED \
--add-opens=java.base/java.math=ALL-UNNAMED \
--add-opens=java.base/java.text=ALL-UNNAMED \
--add-opens=java.base/java.time=ALL-UNNAMED \
--add-opens=java.base/java.sql=ALL-UNNAMED \
--add-opens=java.base/jdk.internal.misc=ALL-UNNAMED"

# 通过 spring.profiles.active 激活 example 配置；数据库/Redis 等通过环境变量注入
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE:-example}"]
