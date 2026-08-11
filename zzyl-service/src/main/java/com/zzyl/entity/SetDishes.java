package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 套餐菜品关联表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "套餐菜品关联表")
public class SetDishes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 套餐编号
     */
    @ApiModelProperty(value = "套餐编号")
    private Long setId;

    /**
     * 菜品编号
     */
    @ApiModelProperty(value = "菜品编号")
    private Long dishesId;
}
