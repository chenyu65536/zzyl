package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Floor extends BaseEntity {

    /**
     * 楼层名称
     */
    @ApiModelProperty(value = "楼层名称")
    private String name;

    /**
     * 楼层编号
     */
    @ApiModelProperty(value = "楼层编号")
    private Integer code;

    /**
     * 楼栋编号
     */
    @ApiModelProperty("楼栋编号")
    private Long buildingId;
}

