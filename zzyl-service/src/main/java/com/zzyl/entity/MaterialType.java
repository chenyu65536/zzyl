package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物资类别表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "物资类别表")
public class MaterialType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 物资类别名称
     */
    @ApiModelProperty(value = "物资类别名称")
    private String name;

    /**
     * 删除状态 0未删除 1已删除
     */
    @ApiModelProperty(value = "删除状态 0未删除 1已删除")
    private Integer delFlag;
}
