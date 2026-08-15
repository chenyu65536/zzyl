package com.zzyl.dto;

import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AlertRuleDto extends BaseDto {
    /**
     * 产品ID
     */
    @ApiModelProperty(value = "产品ID")
    @NotBlank(message = "产品不能为空")
    private String productKey;

    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    private String productName;

    /**
     * 模块ID
     */
    @ApiModelProperty(value = "模块ID")
    private String moduleId;

    /**
     * 模块名称
     */
    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    /**
     * 功能名称
     */
    @ApiModelProperty(value = "功能名称")
    private String functionName;

    /**
     * 功能ID
     */
    @ApiModelProperty(value = "功能ID")
    private String functionId;

    /**
     * 相关设备
     */
    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "设备名称")
    String deviceName;

    /**
     * 规则名称
     */
    @ApiModelProperty(value = "规则名称")
    @NotBlank(message = "规则名称不能为空")
    @Size(max = 100, message = "规则名称不能超过100个字符")
    private String alertRuleName;

    /**
     * 统计字段
     */
    @ApiModelProperty(value = "统计字段")
    private String statisticField;

    /**
     * 操作符
     */
    @ApiModelProperty(value = "操作符")
    @NotBlank(message = "操作符不能为空")
    private String operator;

    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    @NotNull(message = "阈值不能为空")
    private Float value;

    /**
     * 持续时间
     */
    @ApiModelProperty(value = "持续时间")
    private Integer duration;

    /**
     * 数据聚合周期
     */
    @ApiModelProperty(value = "数据聚合周期")
    private Integer dataAggregationPeriod;

    /**
     * 告警生效周期
     */
    @ApiModelProperty(value = "告警生效周期")
    private String alertEffectivePeriod;

    /**
     * 告警静默期
     */
    @ApiModelProperty(value = "告警静默期")
    private Integer alertSilentPeriod;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态 0-无效 1-有效")
    private Integer status;

}