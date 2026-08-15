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
 * 紧急联系人DTO
 */
@Data
@ApiModel(description = "紧急联系人DTO")
public class EmergencyContactDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号", required = true)
    @NotNull(message = "老人不能为空")
    private Long elderId;

    @ApiModelProperty(value = "紧急联系人姓名", required = true)
    @NotBlank(message = "紧急联系人姓名不能为空")
    @Size(max = 50, message = "紧急联系人姓名不能超过50个字符")
    private String name;

    @ApiModelProperty(value = "紧急联系人电话", required = true)
    @NotBlank(message = "紧急联系人电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @ApiModelProperty(value = "紧急联系人邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty(value = "与老人关系")
    private String relation;

    @ApiModelProperty(value = "是否接收消息（Y/N）")
    private String receiveFlag;

    @ApiModelProperty(value = "备注")
    private String remark;
}
