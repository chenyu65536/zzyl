package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 老人健康信息DTO
 */
@Data
@ApiModel(description = "老人健康信息DTO")
public class ElderHealthInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号", required = true)
    @NotNull(message = "老人不能为空")
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

    @ApiModelProperty(value = "备注")
    private String remark;
}
