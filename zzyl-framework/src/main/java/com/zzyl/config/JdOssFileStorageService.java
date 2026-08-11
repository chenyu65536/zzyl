package com.zzyl.config;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.zzyl.properties.JdOssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 京东云对象存储文件服务（基于 S3 兼容协议）
 * 替代原 OSSAliyunFileStorageService
 */
@Slf4j
@Component
@Lazy
public class JdOssFileStorageService {

    @Autowired(required = false)
    AmazonS3 amazonS3;

    @Autowired
    JdOssProperties jdOssProperties;

    /**
     * 上传文件到京东云 OSS
     *
     * @param objectName  文件名（可包含路径，如 2024/01/abc.jpg）
     * @param inputStream 文件输入流
     * @return 文件访问 URL
     */
    public String store(String objectName, InputStream inputStream) {
        if (inputStream == null) {
            log.error("上传文件：objectName {} 文件流为空", objectName);
            return null;
        }
        if (amazonS3 == null) {
            log.error("上传文件失败：AmazonS3 客户端未初始化（OSS 凭证未配置或无效）");
            return null;
        }

        log.info("京东云 OSS 文件上传开始：{}", objectName);
        try {
            String bucketName = jdOssProperties.getBucketName();

            ObjectMetadata metadata = new ObjectMetadata();
            // 根据扩展名设置 ContentType
            String contentType = guessContentType(objectName);
            if (contentType != null) {
                metadata.setContentType(contentType);
            }

            PutObjectResult result = amazonS3.putObject(bucketName, objectName, inputStream, metadata);
            if (result != null) {
                log.info("京东云 OSS 文件上传成功：{}", objectName);
            }
        } catch (AmazonServiceException e) {
            log.error("京东云 OSS 文件上传失败：{}", e.getErrorMessage());
            return null;
        }

        // 文件访问路径：https://{bucketName}.{endpoint}/{objectName}
        StringBuilder urlBuilder = new StringBuilder("https://");
        urlBuilder.append(jdOssProperties.getBucketName())
                .append(".")
                .append(jdOssProperties.getEndpoint())
                .append("/")
                .append(objectName);
        return urlBuilder.toString();
    }

    /**
     * 根据 URL 删除文件
     *
     * @param pathUrl 文件完整 URL
     */
    public void delete(String pathUrl) {
        if (amazonS3 == null) {
            log.warn("删除文件失败：AmazonS3 客户端未初始化");
            return;
        }
        String prefix = "https://" + jdOssProperties.getBucketName() + "." + jdOssProperties.getEndpoint() + "/";
        String key = pathUrl.replace(prefix, "");
        log.info("京东云 OSS 删除文件：{}", key);

        try {
            List<DeleteObjectsRequest.KeyVersion> keys = new ArrayList<>();
            keys.add(new DeleteObjectsRequest.KeyVersion(key));
            DeleteObjectsRequest request = new DeleteObjectsRequest(jdOssProperties.getBucketName())
                    .withKeys(keys);
            amazonS3.deleteObjects(request);
            log.info("京东云 OSS 文件删除成功：{}", key);
        } catch (AmazonServiceException e) {
            log.error("京东云 OSS 文件删除失败：{}", e.getErrorMessage());
        }
    }

    /**
     * 根据文件扩展名猜测 ContentType
     */
    private String guessContentType(String fileName) {
        if (fileName == null) return null;
        String lower = fileName.toLowerCase();
        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return "image/jpeg";
        if (lower.endsWith(".png")) return "image/png";
        if (lower.endsWith(".gif")) return "image/gif";
        if (lower.endsWith(".bmp")) return "image/bmp";
        if (lower.endsWith(".svg")) return "image/svg+xml";
        if (lower.endsWith(".pdf")) return "application/pdf";
        return null;
    }
}
