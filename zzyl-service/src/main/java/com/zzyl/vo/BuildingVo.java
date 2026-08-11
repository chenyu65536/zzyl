package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 楼栋VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "楼栋VO")
public class BuildingVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "楼栋名称")
    private String name;

    @ApiModelProperty(value = "楼栋编码")
    private String code;

    @ApiModelProperty(value = "排序号")
    private Integer sortNo;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "楼层列表")
    private List<FloorVo> floorList;
}
