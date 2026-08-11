package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 员工VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "员工VO")
public class StaffVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工姓名")
    private String name;

    @ApiModelProperty(value = "员工电话")
    private String phone;

    @ApiModelProperty(value = "员工邮箱")
    private String email;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "部门编号")
    private String deptNo;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "离职标识（0在职,1离职）")
    private String leaveFlag;
}
