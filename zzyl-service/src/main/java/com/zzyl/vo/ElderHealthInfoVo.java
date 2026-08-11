package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 老人健康信息VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "老人健康信息VO")
public class ElderHealthInfoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    @ApiModelProperty(value = "自理情况")
    private String selfCare;

    @ApiModelProperty(value = "视力")
    private String vision;

    @ApiModelProperty(value = "听力")
    private String hearing;

    @ApiModelProperty(value = "主治医院")
    private String hospital;

    @ApiModelProperty(value = "主治医师")
    private String doctor;

    @ApiModelProperty(value = "医院电话")
    private String hospitalPhone;

    @ApiModelProperty(value = "过敏药物")
    private String allergyDrug;

    @ApiModelProperty(value = "病史")
    private String medicalHistory;

    @ApiModelProperty(value = "主要疾病")
    private String majorDisease;
}
