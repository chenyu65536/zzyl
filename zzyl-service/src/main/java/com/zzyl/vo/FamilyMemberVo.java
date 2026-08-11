package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 老人家属VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "老人家属VO")
public class FamilyMemberVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    @ApiModelProperty(value = "家属姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    private String idNum;

    @ApiModelProperty(value = "家属电话")
    private String phone;

    @ApiModelProperty(value = "家属邮箱")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "与老人关系")
    private String relation;

    @ApiModelProperty(value = "是否接收消息（Y/N）")
    private String receiveFlag;
}
