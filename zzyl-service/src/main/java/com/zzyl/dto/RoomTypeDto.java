package com.zzyl.dto;

import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@ApiModel(description = "房型信息")
public class RoomTypeDto extends BaseDto {

    /**
     * 房型名称
     */
    @ApiModelProperty(value = "房型名称", example = "豪华大床房")
    @NotBlank(message = "房型名称不能为空")
    @Size(max = 50, message = "房型名称不能超过50个字符")
    private String name;

    /**
     * 床位数量
     */
    @ApiModelProperty(value = "床位数量", example = "2")
    @NotNull(message = "床位数量不能为空")
    @Min(value = 1, message = "床位数量最小为1")
    private Integer bedCount;

    /**
     * 床位费用
     */
    @ApiModelProperty(value = "床位费用", example = "399.99")
    @NotNull(message = "床位费用不能为空")
    @DecimalMin(value = "0", message = "床位费用不能为负数")
    private BigDecimal price;

    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍", example = "简短的房型介绍")
    private String introduction;

    /**
     * 照片
     */
    @ApiModelProperty(value = "照片", example = "http://image.com/roomtype.jpg")
    private String photo;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称", example = "豪华型")
    private String typeName;

    /**
     * 状态，0：禁用，1：启用
     */
    @ApiModelProperty(value = "状态，0：禁用，1：启用", example = "1")
    private Integer status;
}
