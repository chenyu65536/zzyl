
package com.zzyl.dto;

import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 客户老人关联实体类
 */
@Data
@ApiModel(value = "MemberElderDto", description = "客户老人关联实体类")
public class MemberElderDto extends BaseDto {

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long memberId;

    /**
     * 老人id
     */
    @ApiModelProperty(value = "老人id")
    private Long elderId;



    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    @Size(max = 50, message = "姓名不能超过50个字符")
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp = "(^\\d{15}$)|(^\\d{17}[0-9Xx]$)", message = "身份证号格式不正确")
    private String idCard;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;


    /**
     * 关系
     */
    @ApiModelProperty(value = "关系")
    private String refName;

    /**
     * 关系Id
     */
    @ApiModelProperty(value = "关系Id")
    private String refId;
}


