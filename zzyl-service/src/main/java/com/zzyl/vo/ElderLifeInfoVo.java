package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 老人生活档案VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "老人生活档案VO")
public class ElderLifeInfoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    @ApiModelProperty(value = "饮食禁忌")
    private String dietTaboo;

    @ApiModelProperty(value = "生活习惯")
    private String livingHabit;

    @ApiModelProperty(value = "兴趣爱好")
    private String hobby;

    @ApiModelProperty(value = "宗教信仰")
    private String religion;
}
