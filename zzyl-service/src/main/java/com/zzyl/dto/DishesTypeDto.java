package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜品类别DTO
 */
@Data
@ApiModel(description = "菜品类别DTO")
public class DishesTypeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜品类别名称", required = true)
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;
}
