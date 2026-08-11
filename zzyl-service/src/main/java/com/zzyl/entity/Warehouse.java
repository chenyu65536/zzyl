package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仓库表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "仓库表")
public class Warehouse extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称")
    private String name;

    /**
     * 仓库管理员编号(员工)
     */
    @ApiModelProperty(value = "仓库管理员编号")
    private Long staffId;

    /**
     * 删除状态 0未删除 1已删除
     */
    @ApiModelProperty(value = "删除状态 0未删除 1已删除")
    private Integer delFlag;
}
