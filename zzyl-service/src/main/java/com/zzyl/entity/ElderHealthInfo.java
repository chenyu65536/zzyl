package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 老人健康信息表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "老人健康信息表")
public class ElderHealthInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    /**
     * 自理情况
     */
    @ApiModelProperty(value = "自理情况")
    private String selfCare;

    /**
     * 视力
     */
    @ApiModelProperty(value = "视力")
    private String vision;

    /**
     * 听力
     */
    @ApiModelProperty(value = "听力")
    private String hearing;

    /**
     * 主治医院
     */
    @ApiModelProperty(value = "主治医院")
    private String hospital;

    /**
     * 主治医师
     */
    @ApiModelProperty(value = "主治医师")
    private String doctor;

    /**
     * 医院电话
     */
    @ApiModelProperty(value = "医院电话")
    private String hospitalPhone;

    /**
     * 过敏药物
     */
    @ApiModelProperty(value = "过敏药物")
    private String allergyDrug;

    /**
     * 病史
     */
    @ApiModelProperty(value = "病史")
    private String medicalHistory;

    /**
     * 主要疾病
     */
    @ApiModelProperty(value = "主要疾病")
    private String majorDisease;
}
