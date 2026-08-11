package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 物资VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "物资VO")
public class MaterialVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物资类别编号")
    private Long typeId;

    @ApiModelProperty(value = "物资类别名称")
    private String typeName;

    @ApiModelProperty(value = "物资名称")
    private String name;

    @ApiModelProperty(value = "物资规格")
    private String spec;

    @ApiModelProperty(value = "物资单价")
    private BigDecimal price;

    @ApiModelProperty(value = "库存预警阈值")
    private Integer warnThreshold;
}
