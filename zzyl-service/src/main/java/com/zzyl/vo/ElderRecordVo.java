package com.zzyl.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 老人档案聚合详情VO
 */
@Data
@ApiModel(description = "老人档案聚合详情VO")
public class ElderRecordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "头像")
    private String image;

    @ApiModelProperty(value = "身份证号")
    private String idCardNo;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "床位编号")
    private String bedNumber;

    @ApiModelProperty(value = "状态（0：禁用，1:启用 2:请假 3:退住中 4入住中 5已退住）")
    private Integer status;

    @ApiModelProperty(value = "健康信息")
    private ElderHealthInfoVo healthInfo;

    @ApiModelProperty(value = "生活档案")
    private ElderLifeInfoVo lifeInfo;

    @ApiModelProperty(value = "最新体检记录")
    private ElderHealthDataVo latestHealthData;

    @ApiModelProperty(value = "体检记录列表")
    private List<ElderHealthDataVo> healthDataList;

    @ApiModelProperty(value = "紧急联系人列表")
    private List<EmergencyContactVo> emergencyContacts;

    @ApiModelProperty(value = "家属列表")
    private List<FamilyMemberVo> familyMembers;

    @ApiModelProperty(value = "变更记录列表")
    private List<ElderRecordLogVo> changeLogs;
}
