package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 餐饮套餐VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "餐饮套餐VO")
public class CateringSetVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "套餐名称")
    private String name;

    @ApiModelProperty(value = "月套餐费用")
    private BigDecimal monthPrice;

    @ApiModelProperty(value = "套餐内菜品名称(逗号拼接)")
    private String dishesNames;

    @ApiModelProperty(value = "套餐内菜品列表")
    private List<DishesVo> dishesList;
}
