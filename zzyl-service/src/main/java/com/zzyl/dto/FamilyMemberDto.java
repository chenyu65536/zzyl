package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 老人家属DTO
 */
@Data
@ApiModel(description = "老人家属DTO")
public class FamilyMemberDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号", required = true)
    private Long elderId;

    @ApiModelProperty(value = "家属姓名", required = true)
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idNum;

    @ApiModelProperty(value = "家属电话", required = true)
    private String phone;

    @ApiModelProperty(value = "家属邮箱")
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
