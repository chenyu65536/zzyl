package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 老人档案变更记录VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "老人档案变更记录VO")
public class ElderRecordLogVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    @ApiModelProperty(value = "变更类型")
    private String changeType;

    @ApiModelProperty(value = "变更内容描述")
    private String changeContent;
}
