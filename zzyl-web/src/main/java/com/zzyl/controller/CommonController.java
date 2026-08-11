package com.zzyl.controller;

import com.zzyl.base.ResponseResult;
import com.zzyl.config.JdOssFileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    private JdOssFileStorageService fileStorageService;

    /**
     * 允许上传的图片扩展名白名单（不含 svg：svg 在浏览器可直接执行脚本，存在存储型 XSS 风险）
     */
    private static final List<String> ALLOWED_IMAGE_EXTENSIONS =
            Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp");

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 上传结果
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public ResponseResult<String> upload(
            @ApiParam(value = "上传的文件", required = true)
            @RequestPart("file") MultipartFile file) {

        // 修改点：上传安全加固——空校验、大小限制、图片内容校验、扩展名白名单，防御未授权图床滥用与存储型 XSS
        if (file == null || file.isEmpty()) {
            return ResponseResult.error("上传文件不能为空");
        }
        // 单文件大小上限 10MB，防止资源耗尽（图床滥用）
        long maxSize = 10L * 1024 * 1024;
        if (file.getSize() > maxSize) {
            return ResponseResult.error("上传文件大小不能超过10MB");
        }

        String originalFilename = file.getOriginalFilename();
        // 文件名健壮性：必须含扩展名分隔符，避免 lastIndexOf('.') 返回 -1 导致 StringIndexOutOfBoundsException（500）
        if (!org.springframework.util.StringUtils.hasText(originalFilename)
                || originalFilename.lastIndexOf('.') < 0) {
            return ResponseResult.error("文件名不合法");
        }
        // 扩展名白名单（仅图片，排除 svg/html/js 等可执行/脚本类型，防止存储型 XSS）
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.')).toLowerCase();
        if (!ALLOWED_IMAGE_EXTENSIONS.contains(extension)) {
            return ResponseResult.error("仅支持上传 jpg/jpeg/png/gif/bmp 格式的图片");
        }
        // 内容校验：必须为真实图片，防止伪装扩展名上传恶意内容
        try (InputStream inputStream = file.getInputStream()) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            if (bufferedImage == null) {
                return ResponseResult.error("文件内容不是有效图片");
            }
        } catch (IOException e) {
            log.error("文件上传读取失败, fileName={}", originalFilename, e);
            return ResponseResult.error("文件上传失败");
        }

        String fileName = UUID.randomUUID().toString() + extension;
        String filePath;
        // 修改点：store 可能抛出 IOException，显式捕获并返回错误，避免未处理异常导致 500
        try (InputStream storeStream = file.getInputStream()) {
            filePath = fileStorageService.store(fileName, storeStream);
        } catch (IOException e) {
            log.error("文件存储失败, fileName={}", fileName, e);
            return ResponseResult.error("文件上传失败");
        }

        return ResponseResult.success("", filePath);
    }

}
