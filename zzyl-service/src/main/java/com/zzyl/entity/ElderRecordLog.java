package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 老人档案变更记录表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "老人档案变更记录表")
public class ElderRecordLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    /**
     * 变更类型（基础信息修改/健康档案修改/生活档案修改/联系人变更/家属变更/档案作废/档案恢复）
     */
    @ApiModelProperty(value = "变更类型")
    private String changeType;

    /**
     * 变更内容描述
     */
    @ApiModelProperty(value = "变更内容描述")
    private String changeContent;
}
