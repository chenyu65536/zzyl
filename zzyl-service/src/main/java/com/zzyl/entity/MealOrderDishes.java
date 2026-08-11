package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 订餐菜品明细表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "订餐菜品明细表")
public class MealOrderDishes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订餐编号
     */
    @ApiModelProperty(value = "订餐编号")
    private Long mealOrderId;

    /**
     * 菜品编号
     */
    @ApiModelProperty(value = "菜品编号")
    private Long dishesId;

    /**
     * 菜品名称(下单时快照)
     */
    @ApiModelProperty(value = "菜品名称")
    private String dishesName;

    /**
     * 菜品单价(下单时快照)
     */
    @ApiModelProperty(value = "菜品单价")
    private BigDecimal dishesPrice;

    /**
     * 份数
     */
    @ApiModelProperty(value = "份数")
    private Integer orderNum;

    /**
     * 是否套餐内菜品 0否 1是
     */
    @ApiModelProperty(value = "是否套餐内菜品 0否 1是")
    private Integer setFlag;

    /**
     * 应付金额(单价*份数)
     */
    @ApiModelProperty(value = "应付金额")
    private BigDecimal totalAmount;

    /**
     * 实付金额(套餐内菜品减免一份价格)
     */
    @ApiModelProperty(value = "实付金额")
    private BigDecimal reallyAmount;
}
