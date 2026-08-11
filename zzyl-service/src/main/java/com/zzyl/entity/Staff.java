package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 员工表（业务员工信息，区别于sys_user系统用户）
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "员工表")
public class Staff extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 员工姓名
     */
    @ApiModelProperty(value = "员工姓名")
    private String name;

    /**
     * 员工电话
     */
    @ApiModelProperty(value = "员工电话")
    private String phone;

    /**
     * 员工邮箱
     */
    @ApiModelProperty(value = "员工邮箱")
    private String email;

    /**
     * 密码（加密存储）
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    /**
     * 部门编号
     */
    @ApiModelProperty(value = "部门编号")
    private String deptNo;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 离职标识（Y/N）
     */
    @ApiModelProperty(value = "离职标识")
    private String leaveFlag;
}
