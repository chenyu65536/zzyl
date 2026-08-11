package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 员工DTO
 */
@Data
@ApiModel(description = "员工DTO")
public class StaffDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工姓名", required = true)
    private String name;

    @ApiModelProperty(value = "员工电话", required = true)
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

    @ApiModelProperty(value = "备注")
    private String remark;
}
