package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 菜品VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "菜品VO")
public class DishesVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜品类别编号")
    private Long typeId;

    @ApiModelProperty(value = "菜品类别名称")
    private String typeName;

    @ApiModelProperty(value = "菜品名称")
    private String name;

    @ApiModelProperty(value = "菜品单价")
    private BigDecimal price;

    @ApiModelProperty(value = "菜品图片")
    private String image;
}
