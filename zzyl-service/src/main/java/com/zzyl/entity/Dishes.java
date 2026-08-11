package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 菜品表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "菜品表")
public class Dishes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜品类别编号
     */
    @ApiModelProperty(value = "菜品类别编号")
    private Long typeId;

    /**
     * 菜品名称
     */
    @ApiModelProperty(value = "菜品名称")
    private String name;

    /**
     * 菜品单价
     */
    @ApiModelProperty(value = "菜品单价")
    private BigDecimal price;

    /**
     * 菜品图片
     */
    @ApiModelProperty(value = "菜品图片")
    private String image;

    /**
     * 删除状态 0未删除 1已删除
     */
    @ApiModelProperty(value = "删除状态 0未删除 1已删除")
    private Integer delFlag;
}
