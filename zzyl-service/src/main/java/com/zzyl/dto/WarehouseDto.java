package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 仓库DTO
 */
@Data
@ApiModel(description = "仓库DTO")
public class WarehouseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库名称", required = true)
    @NotBlank(message = "仓库名称不能为空")
    @Size(max = 50, message = "仓库名称不能超过50个字符")
    private String name;

    @ApiModelProperty(value = "仓库管理员编号")
    private Long staffId;

    @ApiModelProperty(value = "备注")
    private String remark;
}
