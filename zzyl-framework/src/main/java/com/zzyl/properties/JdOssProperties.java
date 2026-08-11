package com.zzyl.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 京东云对象存储配置属性
 * 绑定 zzyl.framework.oss 配置项
 */
@Data
@Component
@ConfigurationProperties(prefix = "zzyl.framework.oss")
public class JdOssProperties {
    /**
     * OSS 外网访问 Endpoint（如 s3.cn-north-1.jdcloud-oss.com）
     */
    private String endpoint;

    /**
     * 京东云 AccessKey ID
     */
    private String accessKeyId;

    /**
     * 京东云 AccessKey Secret
     */
    private String accessKeySecret;

    /**
     * OSS Bucket 名称（京东云称为"空间名称"）
     */
    private String bucketName;

    /**
     * 地域标识（从 endpoint 中提取，如 cn-north-1）
     */
    private String region;
}
