package com.zzyl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 请假申请DTO
 */
@Data
public class LeaveDto {

    /**
     * 请假编码
     */
    @ApiModelProperty(value = "请假编码")
    private String code;

    /**
     * 操作人id
     */
    @ApiModelProperty(value = "操作人id")
    private String assigneeId;

    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    private String taskId;
}
