package com.zzyl.dto;

import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 护理项目数据传输对象
 */
@Data
public class NursingProjectDto extends BaseDto {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotBlank(message = "项目名称不能为空")
    @Size(max = 50, message = "项目名称不能超过50个字符")
    private String name;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer orderNo;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    @NotBlank(message = "单位不能为空")
    private String unit;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "0", message = "价格不能为负数")
    private BigDecimal price;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String image;

    /**
     * 护理要求
     */
    @ApiModelProperty(value = "护理要求")
    private String nursingRequirement;

    /**
     * 状态（0：禁用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：禁用，1：启用）")
    private Integer status;
}
