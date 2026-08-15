package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 员工DTO
 */
@Data
@ApiModel(description = "员工DTO")
public class StaffDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工姓名", required = true)
    @NotBlank(message = "员工姓名不能为空")
    @Size(max = 50, message = "员工姓名不能超过50个字符")
    private String name;

    @ApiModelProperty(value = "员工电话", required = true)
    @NotBlank(message = "员工电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @ApiModelProperty(value = "员工邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "部门编号")
    private String deptNo;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "备注")
    private String remark;
}
