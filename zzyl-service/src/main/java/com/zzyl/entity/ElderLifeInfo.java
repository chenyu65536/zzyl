package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 老人生活档案表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "老人生活档案表")
public class ElderLifeInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    /**
     * 饮食禁忌
     */
    @ApiModelProperty(value = "饮食禁忌")
    private String dietTaboo;

    /**
     * 生活习惯
     */
    @ApiModelProperty(value = "生活习惯")
    private String livingHabit;

    /**
     * 兴趣爱好
     */
    @ApiModelProperty(value = "兴趣爱好")
    private String hobby;

    /**
     * 宗教信仰
     */
    @ApiModelProperty(value = "宗教信仰")
    private String religion;
}
