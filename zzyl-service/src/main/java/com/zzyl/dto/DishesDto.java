package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜品DTO
 */
@Data
@ApiModel(description = "菜品DTO")
public class DishesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜品类别编号", required = true)
    private Long typeId;

    @ApiModelProperty(value = "菜品名称", required = true)
    private String name;

    @ApiModelProperty(value = "菜品单价", required = true)
    private BigDecimal price;

    @ApiModelProperty(value = "菜品图片")
    private String image;

    @ApiModelProperty(value = "备注")
    private String remark;
}
