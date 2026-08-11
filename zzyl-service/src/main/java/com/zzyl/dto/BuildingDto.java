package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 楼栋DTO
 */
@Data
@ApiModel(description = "楼栋DTO")
public class BuildingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "楼栋名称", required = true)
    private String name;

    @ApiModelProperty(value = "楼栋编码", required = true)
    private String code;

    @ApiModelProperty(value = "排序号")
    private Integer sortNo;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;
}
