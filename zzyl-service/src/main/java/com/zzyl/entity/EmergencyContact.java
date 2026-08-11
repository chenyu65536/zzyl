package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 紧急联系人表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "紧急联系人表")
public class EmergencyContact extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    /**
     * 紧急联系人姓名
     */
    @ApiModelProperty(value = "紧急联系人姓名")
    private String name;

    /**
     * 紧急联系人电话
     */
    @ApiModelProperty(value = "紧急联系人电话")
    private String phone;

    /**
     * 紧急联系人邮箱
     */
    @ApiModelProperty(value = "紧急联系人邮箱")
    private String email;

    /**
     * 与老人关系
     */
    @ApiModelProperty(value = "与老人关系")
    private String relation;

    /**
     * 是否接收消息（Y/N）
     */
    @ApiModelProperty(value = "是否接收消息")
    private String receiveFlag;
}
