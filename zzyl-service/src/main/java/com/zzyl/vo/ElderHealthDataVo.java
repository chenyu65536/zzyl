package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 老人健康数据(体检记录)VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "老人健康数据VO")
public class ElderHealthDataVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    @ApiModelProperty(value = "身高(cm)")
    private Integer height;

    @ApiModelProperty(value = "体重(kg)")
    private BigDecimal weight;

    @ApiModelProperty(value = "体温(℃)")
    private BigDecimal temperature;

    @ApiModelProperty(value = "心率(次/分)")
    private Integer heartRate;

    @ApiModelProperty(value = "收缩血压(mmHg)")
    private Integer systolicBloodPressure;

    @ApiModelProperty(value = "舒张血压(mmHg)")
    private Integer diastolicBloodPressure;

    @ApiModelProperty(value = "空腹血糖(mmol/L)")
    private BigDecimal fastingBloodGlucose;

    @ApiModelProperty(value = "餐后血糖(mmol/L)")
    private BigDecimal postprandialBloodGlucose;

    @ApiModelProperty(value = "血氧饱和度(%)")
    private Integer bloodOxygenSaturation;

    @ApiModelProperty(value = "总胆固醇(mmol/L)")
    private BigDecimal cholesterol;

    @ApiModelProperty(value = "尿酸(umol/L)")
    private Integer uricAcid;

    @ApiModelProperty(value = "左眼视力")
    private BigDecimal leftEye;

    @ApiModelProperty(value = "右眼视力")
    private BigDecimal rightEye;

    @ApiModelProperty(value = "左耳听力")
    private String leftEar;

    @ApiModelProperty(value = "右耳听力")
    private String rightEar;

    @ApiModelProperty(value = "肌肉率(%)")
    private BigDecimal musclePercentage;

    @ApiModelProperty(value = "体脂率(%)")
    private BigDecimal bodyFatPercentage;

    @ApiModelProperty(value = "腰围(cm)")
    private Integer waistCircumference;

    @ApiModelProperty(value = "臀围(cm)")
    private Integer hipCircumference;

    @ApiModelProperty(value = "水分率(%)")
    private BigDecimal moistureContent;

    @ApiModelProperty(value = "体检日期")
    private LocalDateTime checkDate;
}
