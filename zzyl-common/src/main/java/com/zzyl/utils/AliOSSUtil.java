package com.zzyl.utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

/**
 * 京东云对象存储工具类（基于 S3 兼容协议）
 * 替代原 Aliyun OSS 工具类
 */
@Data
@AllArgsConstructor
public class AliOSSUtil {

    private static final Logger log = LoggerFactory.getLogger(AliOSSUtil.class);

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 文件上传（字节数组）
     *
     * @param bytes      文件字节数组
     * @param objectName 文件名
     * @return 文件访问 URL
     */
    public String upload(byte[] bytes, String objectName) {
        try {
            // 从 endpoint 提取 region（格式：s3.{region}.jdcloud-oss.com）
            String region = extractRegion(endpoint);

            AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);
            AwsClientBuilder.EndpointConfiguration endpointConfig =
                    new AwsClientBuilder.EndpointConfiguration("https://" + endpoint, region);

            AmazonS3 s3Client = AmazonS3Client.builder()
                    .withEndpointConfiguration(endpointConfig)
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .disableChunkedEncoding()
                    .build();

            try {
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(bytes.length);
                PutObjectResult result = s3Client.putObject(bucketName, objectName,
                        new ByteArrayInputStream(bytes), metadata);
                log.info("京东云 OSS 文件上传完成：{}", objectName);
            } catch (AmazonServiceException e) {
                log.error("京东云 OSS 上传失败：Error Code={}, Error Message={}",
                        e.getErrorCode(), e.getErrorMessage());
            } finally {
                s3Client.shutdown();
            }
        } catch (Exception e) {
            log.error("京东云 OSS 客户端异常：{}", e.getMessage());
        }

        // 文件访问路径：https://{bucketName}.{endpoint}/{objectName}
        StringBuilder urlBuilder = new StringBuilder("https://");
        urlBuilder.append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);
        log.info("文件访问地址：{}", urlBuilder);
        return urlBuilder.toString();
    }

    /**
     * 从 endpoint 提取 region
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
