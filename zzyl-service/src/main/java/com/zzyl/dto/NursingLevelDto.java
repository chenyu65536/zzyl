package com.zzyl.dto;

import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@ApiModel(value = "NursingLevelDto对象", description = "护理等级DTO类")
public class NursingLevelDto extends BaseDto {

    /**
     * 等级名称
     */
    @ApiModelProperty(value = "等级名称")
    @NotBlank(message = "等级名称不能为空")
    @Size(max = 50, message = "等级名称不能超过50个字符")
    private String name;

    /**
     * 护理计划名称
     */
    @ApiModelProperty(value = "护理计划名称")
    private String planName;

    /**
     * 护理计划ID
     */
    @ApiModelProperty(value = "护理计划ID")
    @NotNull(message = "护理计划不能为空")
    private Long planId;

    /**
     * 护理费用
     */
    @ApiModelProperty(value = "护理费用")
    @NotNull(message = "护理费用不能为空")
    @DecimalMin(value = "0", message = "护理费用不能为负数")
    private BigDecimal fee;

    /**
     * 状态（0：禁用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：禁用，1：启用）")
    private Integer status;

    /**
     * 等级说明
     */
    @ApiModelProperty(value = "等级说明")
    private String description;
}

