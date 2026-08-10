package com.zzyl.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.zzyl.properties.AliOssConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Slf4j
@Configuration
public class OssAliyunAutoConfig {

    @Autowired
    AliOssConfigProperties aliOssConfigProperties;

    /**
     * 创建阿里云 OSS 客户端。
     * <p>
     * 修改点：改为 @Lazy 懒加载，避免应用启动阶段强制访问 OSS；
     * 同时去掉启动时的 setBucketLogging（需 bucket 所有权，且非必需），
     * 并对建桶逻辑做异常兜底，OSS 凭证失效时仅告警、不阻断应用启动。
     *
     * @return OSS 客户端实例
     */
    @Bean
    @Lazy
    public OSS ossClient() {
        // 修改点：凭证未配置时直接返回 null，避免 OSSClientBuilder.build() 抛 InvalidCredentialsException 阻断启动
        String accessKeyId = aliOssConfigProperties.getAccessKeyId();
        String accessKeySecret = aliOssConfigProperties.getAccessKeySecret();
        if (accessKeyId == null || accessKeyId.isEmpty() || accessKeySecret == null || accessKeySecret.isEmpty()) {
            log.warn("OSS 凭证未配置（accessKeyId/accessKeySecret 为空），跳过 OSSClient 创建，文件上传功能将不可用");
            return null;
        }
        log.info("-----------------开始创建OSSClient--------------------");
        OSS ossClient;
        try {
            ossClient = new OSSClientBuilder().build(aliOssConfigProperties.getEndpoint(),
                    accessKeyId, accessKeySecret);
            // 判断容器是否存在,不存在就创建（bucket 建议在控制台预先创建）
            if (!ossClient.doesBucketExist(aliOssConfigProperties.getBucketName())) {
                CreateBucketRequest createBucketRequest =
                        new CreateBucketRequest(aliOssConfigProperties.getBucketName());
                // 设置为公共可读
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
        } catch (com.aliyun.oss.OSSException | com.aliyun.oss.ClientException e) {
            // 修改点：凭证无效或无 bucket 权限时不再让应用崩溃，仅记录告警
            log.warn("OSS 初始化校验失败（不影响应用启动，上传功能将不可用）: {}", e.getMessage());
            return null;
        }
        log.info("-----------------结束创建OSSClient--------------------");
        return ossClient;
    }


}
