package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜品DTO
 */
@Data
@ApiModel(description = "菜品DTO")
public class DishesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜品类别编号", required = true)
    @NotNull(message = "菜品类别不能为空")
    private Long typeId;

    @ApiModelProperty(value = "菜品名称", required = true)
    @NotBlank(message = "菜品名称不能为空")
    @Size(max = 50, message = "菜品名称不能超过50个字符")
    private String name;

    @ApiModelProperty(value = "菜品单价", required = true)
    @NotNull(message = "菜品单价不能为空")
    @DecimalMin(value = "0", message = "菜品单价不能为负数")
    private BigDecimal price;

    @ApiModelProperty(value = "菜品图片")
    private String image;

    @ApiModelProperty(value = "备注")
    private String remark;
}
