package com.zzyl.exception;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义异常BaseException。
     * 返回自定义异常中的错误代码和错误消息。
     *
     * @param exception 自定义异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleBaseException(BaseException exception) {

        if (ObjectUtil.isNotEmpty(exception.getCause())) {
            log.error("自定义异常处理 -> ", exception);
        }
        return ResponseEntity.ok(MapUtil.<String, Object>builder()
                .put("code", exception.getCode())
                .put("msg", exception.getDefaultMessage())
                .build());
    }

    /**
     * 处理@RequestBody参数校验失败异常（含MethodArgumentNotValidException，为BindException子类）。
     * 返回HTTP响应状态码200，包含错误代码和第一条字段校验错误消息。
     *
     * @param exception 参数绑定校验异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException exception) {

        FieldError fieldError = exception.getBindingResult().getFieldError();
        String msg = ObjectUtil.isNotEmpty(fieldError) ? fieldError.getDefaultMessage() : "请求参数不合法";
        log.warn("参数校验失败 -> {}", msg);
        return ResponseEntity.ok(MapUtil.<String, Object>builder()
                .put("code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                .put("msg", msg)
                .build());
    }

    /**
     * 处理@RequestParam/@PathVariable参数级约束校验失败异常。
     * 返回HTTP响应状态码200，包含错误代码和第一条约束校验错误消息。
     *
     * @param exception 约束校验异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {

        String msg = exception.getConstraintViolations().stream()
                .findFirst()
                .map(ConstraintViolation::getMessage)
                .orElse("请求参数不合法");
        log.warn("参数校验失败 -> {}", msg);
        return ResponseEntity.ok(MapUtil.<String, Object>builder()
                .put("code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                .put("msg", msg)
                .build());
    }

    /**
     * 处理缺少必填请求参数异常。
     * 返回HTTP响应状态码200，包含错误代码和缺失参数名。
     *
     * @param exception 缺少请求参数异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {

        String msg = "缺少必填参数：" + exception.getParameterName();
        log.warn("参数校验失败 -> {}", msg);
        return ResponseEntity.ok(MapUtil.<String, Object>builder()
                .put("code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                .put("msg", msg)
                .build());
    }

    /**
     * 处理请求体不可读（非法JSON）和参数类型不匹配异常。
     * 返回HTTP响应状态码200，包含错误代码和错误消息。
     *
     * @param exception 请求格式异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleRequestFormatException(Exception exception) {

        log.warn("请求参数格式错误 -> {}", exception.getMessage());
        return ResponseEntity.ok(MapUtil.<String, Object>builder()
                .put("code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                .put("msg", "请求参数格式错误")
                .build());
    }

    /**
     * 处理文件上传超过最大限制异常。
     * 返回HTTP响应状态码500，包含错误代码和错误消息。
     *
     * @param exception 文件上传异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {

        if (ObjectUtil.isNotEmpty(exception.getCause())) {
            log.error("文件上传超过最大限制异常 -> ", exception);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MapUtil.<String, Object>builder()
                        .put("code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .put("msg", "上传图片大小不能超过5M，格式需为jpg、png、gif")
                        .build());
    }

    /**
     * 处理其他未知异常。
     * 返回HTTP响应状态码500，包含错误代码和异常堆栈信息。
     *
     * @param exception 未知异常
     * @return 响应数据，包含错误代码和异常堆栈信息
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception exception) {

        if (ObjectUtil.isNotEmpty(exception.getCause())) {
            log.error("其他未知异常 -> ", exception);
        }


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MapUtil.<String, Object>builder()
                        .put("code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .put("msg", ExceptionUtil.stacktraceToString(exception))
                        .build());
    }

    /**
     * 处理FileNotFoundException异常。
     * 返回HTTP响应状态码400，包含错误代码和错误消息。
     *
     * @param exception 文件未找到异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException exception) {

        if (ObjectUtil.isNotEmpty(exception.getCause())) {
            log.error("文件不存在 -> ", exception);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MapUtil.<String, Object>builder()
                        .put("code", HttpStatus.BAD_REQUEST.value())
                        .put("msg", exception.getMessage())
                        .build());
    }

    /**
     * 处理没有权限访问接口异常。
     * 返回HTTP响应状态码401，包含错误代码和错误消息。
     *
     * @param exception 权限访问异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {

        if (ObjectUtil.isNotEmpty(exception.getCause())) {
            log.error("没有权限访问接口异常 -> ", exception);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(MapUtil.<String, Object>builder()
                        .put("code", HttpStatus.UNAUTHORIZED.value())
                        .put("msg", "没有权限访问接口")
                        .build());
    }

    /**
     * 处理运行时异常。
     * 返回HTTP响应状态码500，包含错误代码和错误消息。
     *
     * @param exception 运行时异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {

        if (ObjectUtil.isNotEmpty(exception.getCause())) {
            log.error("其他未知异常 -> ", exception);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MapUtil.<String, Object>builder()
                        .put("code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .put("msg", exception.getMessage())
                        .build());
    }

    /**
     * 处理key重复异常。
     * 返回HTTP响应状态码200，包含错误代码和错误消息。
     *
     * @param exception key重复异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException exception) {

        if (ObjectUtil.isNotEmpty(exception.getCause())) {
            log.error("其他未知异常 -> ", exception);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(MapUtil.<String, Object>builder()
                        .put("code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .put("msg", "操作失败，数据重复")
                        .build());
    }
}
