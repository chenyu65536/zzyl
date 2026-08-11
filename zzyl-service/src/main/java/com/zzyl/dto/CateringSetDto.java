package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    private String name;

    @ApiModelProperty(value = "月套餐费用", required = true)
    private BigDecimal monthPrice;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "套餐内菜品编号列表", required = true)
    private List<Long> dishesIdList;
}
