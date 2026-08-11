package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 老人健康数据表（体检记录）
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "老人健康数据表")
public class ElderHealthData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    /**
     * 身高(cm)
     */
    @ApiModelProperty(value = "身高(cm)")
    private Integer height;

    /**
     * 体重(kg)
     */
    @ApiModelProperty(value = "体重(kg)")
    private BigDecimal weight;

    /**
     * 体温(℃)
     */
    @ApiModelProperty(value = "体温(℃)")
    private BigDecimal temperature;

    /**
     * 心率(次/分)
     */
    @ApiModelProperty(value = "心率(次/分)")
    private Integer heartRate;

    /**
     * 收缩血压(mmHg)
     */
    @ApiModelProperty(value = "收缩血压(mmHg)")
    private Integer systolicBloodPressure;

    /**
     * 舒张血压(mmHg)
     */
    @ApiModelProperty(value = "舒张血压(mmHg)")
    private Integer diastolicBloodPressure;

    /**
     * 空腹血糖(mmol/L)
     */
    @ApiModelProperty(value = "空腹血糖(mmol/L)")
    private BigDecimal fastingBloodGlucose;

    /**
     * 餐后血糖(mmol/L)
     */
    @ApiModelProperty(value = "餐后血糖(mmol/L)")
    private BigDecimal postprandialBloodGlucose;

    /**
     * 血氧饱和度(%)
     */
    @ApiModelProperty(value = "血氧饱和度(%)")
    private Integer bloodOxygenSaturation;

    /**
     * 总胆固醇(mmol/L)
     */
    @ApiModelProperty(value = "总胆固醇(mmol/L)")
    private BigDecimal cholesterol;

    /**
     * 尿酸(umol/L)
     */
    @ApiModelProperty(value = "尿酸(umol/L)")
    private Integer uricAcid;

    /**
     * 左眼视力
     */
    @ApiModelProperty(value = "左眼视力")
    private BigDecimal leftEye;

    /**
     * 右眼视力
     */
    @ApiModelProperty(value = "右眼视力")
    private BigDecimal rightEye;

    /**
     * 左耳听力
     */
    @ApiModelProperty(value = "左耳听力")
    private String leftEar;

    /**
     * 右耳听力
     */
    @ApiModelProperty(value = "右耳听力")
    private String rightEar;

    /**
     * 肌肉率(%)
     */
    @ApiModelProperty(value = "肌肉率(%)")
    private BigDecimal musclePercentage;

    /**
     * 体脂率(%)
     */
    @ApiModelProperty(value = "体脂率(%)")
    private BigDecimal bodyFatPercentage;

    /**
     * 腰围(cm)
     */
    @ApiModelProperty(value = "腰围(cm)")
    private Integer waistCircumference;

    /**
     * 臀围(cm)
     */
    @ApiModelProperty(value = "臀围(cm)")
    private Integer hipCircumference;

    /**
     * 水分率(%)
     */
    @ApiModelProperty(value = "水分率(%)")
    private BigDecimal moistureContent;

    /**
     * 体检日期
     */
    @ApiModelProperty(value = "体检日期")
    private java.time.LocalDateTime checkDate;
}
