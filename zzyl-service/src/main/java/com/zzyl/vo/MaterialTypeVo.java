package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物资类别VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "物资类别VO")
public class MaterialTypeVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物资类别名称")
    private String name;
}
