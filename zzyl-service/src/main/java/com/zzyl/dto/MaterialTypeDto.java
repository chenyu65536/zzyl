package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 物资类别DTO
 */
@Data
@ApiModel(description = "物资类别DTO")
public class MaterialTypeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物资类别名称", required = true)
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;
}
