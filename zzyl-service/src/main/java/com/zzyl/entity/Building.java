package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 楼栋表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "楼栋表")
public class Building extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 楼栋名称
     */
    @ApiModelProperty(value = "楼栋名称")
    private String name;

    /**
     * 楼栋编码
     */
    @ApiModelProperty(value = "楼栋编码")
    private String code;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer sortNo;

    /**
     * 状态（1：启用，0：禁用）
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
}
