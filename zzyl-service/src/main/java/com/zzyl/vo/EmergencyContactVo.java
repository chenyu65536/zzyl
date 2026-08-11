package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 紧急联系人VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "紧急联系人VO")
public class EmergencyContactVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    @ApiModelProperty(value = "紧急联系人姓名")
    private String name;

    @ApiModelProperty(value = "紧急联系人电话")
    private String phone;

    @ApiModelProperty(value = "紧急联系人邮箱")
    private String email;

    @ApiModelProperty(value = "与老人关系")
    private String relation;

    @ApiModelProperty(value = "是否接收消息（Y/N）")
    private String receiveFlag;
}
