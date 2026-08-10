package com.zzyl.handler;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zzyl.utils.UserThreadLocal;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatis-Plus 自动填充处理器
 * 替代原有的 AutoFillInterceptor，利用 MP 原生机制实现 createBy/createTime/updateBy/updateTime 自动填充
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_BY = "createBy";
    private static final String UPDATE_BY = "updateBy";
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = loadUserId();
        LocalDateTime now = LocalDateTime.now();

        this.strictInsertFill(metaObject, CREATE_BY, Long.class, userId);
        this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime.class, now);
        this.strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime.class, now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = loadUserId();
        LocalDateTime now = LocalDateTime.now();

        this.strictUpdateFill(metaObject, UPDATE_BY, Long.class, userId);
        this.strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, now);
    }

    /**
     * 获取当前用户 ID
     */
    private Long loadUserId() {
        Long userId = UserThreadLocal.getUserId();
        if (ObjectUtil.isNotEmpty(userId)) {
            return userId;
        }
        userId = UserThreadLocal.getMgtUserId();
        if (ObjectUtil.isNotEmpty(userId)) {
            return userId;
        }
        return 1L;
    }
}
