package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仓库VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "仓库VO")
public class WarehouseVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库名称")
    private String name;

    @ApiModelProperty(value = "仓库管理员编号")
    private Long staffId;

    @ApiModelProperty(value = "仓库管理员姓名")
    private String staffName;
}
