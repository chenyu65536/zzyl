package com.zzyl.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 库存汇总VO(按物资维度汇总)
 */
@Data
@ApiModel(description = "库存汇总VO")
public class InventoryVo implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "库存总量")
    private Integer totalInventory;

    @ApiModelProperty(value = "库存预警阈值")
    private Integer warnThreshold;

    @ApiModelProperty(value = "是否低库存 0否 1是")
    private Integer lowStock;
}
