package com.zzyl.enums;

import com.zzyl.base.IBasicEnum;

/**
 *  基础枚举
 */
public enum BasicEnum implements IBasicEnum {

    SUCCEED(200,"操作成功"),
    SECURITY_ACCESSDENIED_FAIL(401,"权限不足!"),
    SYSYTEM_FAIL(1503,"系统运行异常"),
    VALID_EXCEPTION(1504,"参数校验异常");

    /**
     * 编码
     */
    private final int code;
    /**
     * 信息
     */
    private final String msg;

    BasicEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
