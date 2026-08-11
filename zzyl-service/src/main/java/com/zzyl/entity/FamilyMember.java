package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 老人民家属表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "老人民家属表")
public class FamilyMember extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    /**
     * 家属姓名
     */
    @ApiModelProperty(value = "家属姓名")
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNum;

    /**
     * 家属电话
     */
    @ApiModelProperty(value = "家属电话")
    private String phone;

    /**
     * 家属邮箱
     */
    @ApiModelProperty(value = "家属邮箱")
    private String email;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

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

    /**
     * 删除状态（Y/N）
     */
    @ApiModelProperty(value = "删除状态")
    private String delFlag;
}
