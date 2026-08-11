package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 出库物资VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "出库物资VO")
public class OutboundMaterialVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "出库登记编号")
    private Long outboundRecordId;

    @ApiModelProperty(value = "入库物资编号(批次)")
    private Long warehouseMaterialId;

    @ApiModelProperty(value = "物资编号")
    private Long materialId;

    @ApiModelProperty(value = "物资名称")
    private String materialName;

    @ApiModelProperty(value = "物资类别名称")
    private String typeName;

    @ApiModelProperty(value = "物资规格")
    private String spec;

    @ApiModelProperty(value = "物资单价")
    private BigDecimal price;

    @ApiModelProperty(value = "出库数量")
    private Integer outboundNum;
}
