package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 订餐菜品明细VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "订餐菜品明细VO")
public class MealOrderDishesVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订餐编号")
    private Long mealOrderId;

    @ApiModelProperty(value = "菜品编号")
    private Long dishesId;

    @ApiModelProperty(value = "菜品名称")
    private String dishesName;

    @ApiModelProperty(value = "菜品单价")
    private BigDecimal dishesPrice;

    @ApiModelProperty(value = "份数")
    private Integer orderNum;

    @ApiModelProperty(value = "是否套餐内菜品 0否 1是")
    private Integer setFlag;

    @ApiModelProperty(value = "应付金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "实付金额")
    private BigDecimal reallyAmount;
}
