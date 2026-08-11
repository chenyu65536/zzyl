package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 紧急联系人DTO
 */
@Data
@ApiModel(description = "紧急联系人DTO")
public class EmergencyContactDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号", required = true)
    private Long elderId;

    @ApiModelProperty(value = "紧急联系人姓名", required = true)
    private String name;

    @ApiModelProperty(value = "紧急联系人电话", required = true)
    private String phone;

    @ApiModelProperty(value = "紧急联系人邮箱")
    private String email;

    @ApiModelProperty(value = "与老人关系")
    private String relation;

    @ApiModelProperty(value = "是否接收消息（Y/N）")
    private String receiveFlag;

    @ApiModelProperty(value = "备注")
    private String remark;
}
