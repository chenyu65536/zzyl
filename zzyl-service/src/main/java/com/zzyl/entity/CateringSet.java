package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 餐饮套餐表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "餐饮套餐表")
public class CateringSet extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 套餐名称
     */
    @ApiModelProperty(value = "套餐名称")
    private String name;

    /**
     * 月套餐费用
     */
    @ApiModelProperty(value = "月套餐费用")
    private BigDecimal monthPrice;

    /**
     * 删除状态 0未删除 1已删除
     */
    @ApiModelProperty(value = "删除状态 0未删除 1已删除")
    private Integer delFlag;
}
