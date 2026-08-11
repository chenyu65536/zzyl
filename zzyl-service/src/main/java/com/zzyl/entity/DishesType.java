package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜品类别表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "菜品类别表")
public class DishesType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜品类别名称
     */
    @ApiModelProperty(value = "菜品类别名称")
    private String name;

    /**
     * 删除状态 0未删除 1已删除
     */
    @ApiModelProperty(value = "删除状态 0未删除 1已删除")
    private Integer delFlag;
}
