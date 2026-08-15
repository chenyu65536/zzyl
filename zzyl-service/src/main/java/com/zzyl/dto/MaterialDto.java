package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(message = "物资类别不能为空")
    private Long typeId;

    @ApiModelProperty(value = "物资名称", required = true)
    @NotBlank(message = "物资名称不能为空")
    @Size(max = 50, message = "物资名称不能超过50个字符")
    private String name;

    @ApiModelProperty(value = "物资规格")
    @Size(max = 50, message = "物资规格不能超过50个字符")
    private String spec;

    @ApiModelProperty(value = "物资单价")
    @DecimalMin(value = "0", message = "物资单价不能为负数")
    private BigDecimal price;

    @ApiModelProperty(value = "库存预警阈值")
    @Min(value = 0, message = "库存预警阈值不能为负数")
    private Integer warnThreshold;

    @ApiModelProperty(value = "备注")
    private String remark;
}
