package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import com.zzyl.entity.AccraditationRecord;
import com.zzyl.entity.Leave;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 请假响应VO
 */
@Data
public class LeaveVo extends BaseVo {

    /**
     * 请假信息
     */
    private Leave leave;

    /**
     * 审核记录
     */
    private List<AccraditationRecord> accraditationRecords;

    /**
     * 单据类型
     */
    private Integer type;

    /**
     * 是否展示请假数据
     */
    @ApiModelProperty(value = "是否展示请假数据")
    private Integer isShow;

    /**
     * 是否显示撤回
     */
    @ApiModelProperty(value = "是否显示撤回")
    private Boolean isRevocation;

    /**
     * 下一个审批人
     */
    private String nextApprover;
}
