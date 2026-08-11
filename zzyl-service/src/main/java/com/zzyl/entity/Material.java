package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 物资表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "物资表")
public class Material extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 物资类别编号
     */
    @ApiModelProperty(value = "物资类别编号")
    private Long typeId;

    /**
     * 物资名称
     */
    @ApiModelProperty(value = "物资名称")
    private String name;

    /**
     * 物资规格
     */
    @ApiModelProperty(value = "物资规格")
    private String spec;

    /**
     * 物资单价
     */
    @ApiModelProperty(value = "物资单价")
    private BigDecimal price;

    /**
     * 库存预警阈值(库存低于该值时提醒)
     */
    @ApiModelProperty(value = "库存预警阈值")
    private Integer warnThreshold;

    /**
     * 删除状态 0未删除 1已删除
     */
    @ApiModelProperty(value = "删除状态 0未删除 1已删除")
    private Integer delFlag;
}
