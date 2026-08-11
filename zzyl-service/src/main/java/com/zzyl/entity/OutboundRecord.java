package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 出库登记表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "出库登记表")
public class OutboundRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库编号
     */
    @ApiModelProperty(value = "仓库编号")
    private Long warehouseId;

    /**
     * 经办人编号(员工)
     */
    @ApiModelProperty(value = "经办人编号")
    private Long staffId;

    /**
     * 领用人编号
     */
    @ApiModelProperty(value = "领用人编号")
    private Long recipientId;

    /**
     * 领用人类型 0员工 1老人
     */
    @ApiModelProperty(value = "领用人类型 0员工 1老人")
    private Integer recipientType;

    /**
     * 物资去向(护理用品领用/办公用品领用等)
     */
    @ApiModelProperty(value = "物资去向")
    private String materialUse;

    /**
     * 出库时间
     */
    @ApiModelProperty(value = "出库时间")
    private LocalDateTime outboundTime;

    /**
     * 出库状态 0待审核 1已通过 2未通过
     */
    @ApiModelProperty(value = "出库状态 0待审核 1已通过 2未通过")
    private Integer status;

    /**
     * 删除状态 0未删除 1已删除
     */
    @ApiModelProperty(value = "删除状态 0未删除 1已删除")
    private Integer delFlag;
}
