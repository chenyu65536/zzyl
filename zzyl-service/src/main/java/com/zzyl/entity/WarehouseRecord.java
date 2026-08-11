package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 入库登记表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "入库登记表")
public class WarehouseRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    @ApiModelProperty(value = "仓库编号")
    private Long warehouseId;

    /**
     * 经办人编号(员工)
     */
    @ApiModelProperty(value = "经办人编号")
    private Long staffId;

    /**
     * 物资来源(采购入库/捐赠入库等)
     */
    @ApiModelProperty(value = "物资来源")
    private String source;

    /**
     * 入库时间
     */
    @ApiModelProperty(value = "入库时间")
    private LocalDateTime warehouseTime;

    /**
     * 入库状态 0待审核 1已通过 2未通过
     */
    @ApiModelProperty(value = "入库状态 0待审核 1已通过 2未通过")
    private Integer status;

    /**
     * 删除状态 0未删除 1已删除
     */
    @ApiModelProperty(value = "删除状态 0未删除 1已删除")
    private Integer delFlag;
}
