package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 老人生活档案DTO
 */
@Data
@ApiModel(description = "老人生活档案DTO")
public class ElderLifeInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号", required = true)
    @NotNull(message = "老人不能为空")
    private Long elderId;

    @ApiModelProperty(value = "饮食禁忌")
    private String dietTaboo;

    @ApiModelProperty(value = "生活习惯")
    private String livingHabit;

    @ApiModelProperty(value = "兴趣爱好")
    private String hobby;

    @ApiModelProperty(value = "宗教信仰")
    private String religion;

    @ApiModelProperty(value = "备注")
    private String remark;
}
