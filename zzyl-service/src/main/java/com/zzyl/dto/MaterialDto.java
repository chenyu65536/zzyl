package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 物资DTO
 */
@Data
@ApiModel(description = "物资DTO")
public class MaterialDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物资类别编号", required = true)
    private Long typeId;

    @ApiModelProperty(value = "物资名称", required = true)
    private String name;

    @ApiModelProperty(value = "物资规格")
    private String spec;

    @ApiModelProperty(value = "物资单价")
    private BigDecimal price;

    @ApiModelProperty(value = "库存预警阈值")
    private Integer warnThreshold;

    @ApiModelProperty(value = "备注")
    private String remark;
}
