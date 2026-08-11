package com.zzyl.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.zzyl.properties.JdOssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 京东云对象存储自动配置（基于 S3 兼容协议）
 * 替代原 Aliyun OSS 配置
 */
@Slf4j
@Configuration
public class JdOssAutoConfig {

    @Autowired
    JdOssProperties jdOssProperties;

    /**
     * 创建 AmazonS3 客户端（对接京东云对象存储）。
     * 京东云 OSS 兼容 AWS S3 协议，使用 aws-java-sdk-s3 即可访问。
     *
     * @return AmazonS3 客户端实例；凭证未配置时返回 null
     */
    @Bean
    @Lazy
    public AmazonS3 amazonS3() {
        String accessKeyId = jdOssProperties.getAccessKeyId();
        String accessKeySecret = jdOssProperties.getAccessKeySecret();
        if (accessKeyId == null || accessKeyId.isEmpty() || accessKeySecret == null || accessKeySecret.isEmpty()) {
            log.warn("京东云 OSS 凭证未配置（accessKeyId/accessKeySecret 为空），跳过 AmazonS3 创建，文件上传功能将不可用");
            return null;
        }

        log.info("-----------------开始创建京东云 AmazonS3 客户端--------------------");
        try {
            String endpoint = jdOssProperties.getEndpoint();
            String region = jdOssProperties.getRegion();
            if (region == null || region.isEmpty()) {
                // 从 endpoint 自动提取 region（格式：s3.{region}.jdcloud-oss.com）
                region = extractRegion(endpoint);
            }

            ClientConfiguration config = new ClientConfiguration();
            AwsClientBuilder.EndpointConfiguration endpointConfig =
                    new AwsClientBuilder.EndpointConfiguration("https://" + endpoint, region);

            AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);
            AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

            AmazonS3 s3 = AmazonS3Client.builder()
                    .withEndpointConfiguration(endpointConfig)
                    .withClientConfiguration(config)
                    .withCredentials(credentialsProvider)
                    .disableChunkedEncoding()
                    .build();

            // 检查 Bucket 是否存在
            try {
                if (!s3.doesBucketExistV2(jdOssProperties.getBucketName())) {
                    log.warn("京东云 OSS Bucket [{}] 不存在，建议在控制台手动创建", jdOssProperties.getBucketName());
                }
            } catch (Exception e) {
                log.warn("京东云 OSS Bucket 检查失败: {}", e.getMessage());
            }

            log.info("-----------------京东云 AmazonS3 客户端创建完成--------------------");
            return s3;
        } catch (Exception e) {
            log.warn("京东云 OSS 客户端创建失败（不影响应用启动，上传功能将不可用）: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 从 endpoint 中提取 region
     * 格式：s3.{region}.jdcloud-oss.com → region
     */
    private String extractRegion(String endpoint) {
        if (endpoint == null) return "cn-north-1";
        String[] parts = endpoint.split("\\.");
        if (parts.length >= 4) {
            return parts[1];
        }
        return "cn-north-1";
    }
}
