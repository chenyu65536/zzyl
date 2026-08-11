package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出库物资表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "出库物资表")
public class OutboundMaterial extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 出库登记编号
     */
    @ApiModelProperty(value = "出库登记编号")
    private Long outboundRecordId;

    /**
     * 入库物资编号(批次)
     */
    @ApiModelProperty(value = "入库物资编号")
    private Long warehouseMaterialId;

    /**
     * 物资编号
     */
    @ApiModelProperty(value = "物资编号")
    private Long materialId;

    /**
     * 出库数量
     */
    @ApiModelProperty(value = "出库数量")
    private Integer outboundNum;
}
