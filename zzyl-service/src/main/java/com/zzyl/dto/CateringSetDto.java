package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 餐饮套餐DTO
 */
@Data
@ApiModel(description = "餐饮套餐DTO")
public class CateringSetDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "套餐名称", required = true)
    @NotBlank(message = "套餐名称不能为空")
    @Size(max = 50, message = "套餐名称不能超过50个字符")
    private String name;

    @ApiModelProperty(value = "月套餐费用", required = true)
    @NotNull(message = "月套餐费用不能为空")
    @DecimalMin(value = "0", message = "月套餐费用不能为负数")
    private BigDecimal monthPrice;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "套餐内菜品编号列表", required = true)
    @NotEmpty(message = "套餐内菜品不能为空")
    private List<Long> dishesIdList;
}
