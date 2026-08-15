package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 老人家属DTO
 */
@Data
@ApiModel(description = "老人家属DTO")
public class FamilyMemberDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号", required = true)
    @NotNull(message = "老人不能为空")
    private Long elderId;

    @ApiModelProperty(value = "家属姓名", required = true)
    @NotBlank(message = "家属姓名不能为空")
    @Size(max = 50, message = "家属姓名不能超过50个字符")
    private String name;

    @ApiModelProperty(value = "身份证号")
    @Pattern(regexp = "(^\\d{15}$)|(^\\d{17}[0-9Xx]$)", message = "身份证号格式不正确")
    private String idNum;

    @ApiModelProperty(value = "家属电话", required = true)
    @NotBlank(message = "家属电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @ApiModelProperty(value = "家属邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "与老人关系")
    private String relation;

    @ApiModelProperty(value = "是否接收消息（Y/N）")
    private String receiveFlag;

    @ApiModelProperty(value = "备注")
    private String remark;
}
