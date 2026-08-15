package com.zzyl.dto;

import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 老人实体类
 */
@Data
public class ElderDto extends BaseDto {

    /**
     * 姓名
     */
    @NotBlank(message = "老人姓名不能为空")
    @Size(max = 50, message = "老人姓名不能超过50个字符")
    private String name;

    /**
     * 头像
     */
    private String image;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 身份证号
     */
    @Pattern(regexp = "(^\\d{15}$)|(^\\d{17}[0-9Xx]$)", message = "身份证号格式不正确")
    private String idCardNo;

    /**
     * 手机号
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "性别")
    private String sex;
}

