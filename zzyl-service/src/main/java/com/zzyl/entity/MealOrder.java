package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订餐表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "订餐表")
public class MealOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 老人编号
     */
    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    /**
     * 套餐编号(下单选择的套餐,可为空)
     */
    @ApiModelProperty(value = "套餐编号")
    private Long cateringSetId;

    /**
     * 送餐人编号(员工)
     */
    @ApiModelProperty(value = "送餐人编号")
    private Long staffId;

    /**
     * 送餐时间
     */
    @ApiModelProperty(value = "送餐时间")
    private LocalDateTime deliverTime;

    /**
     * 就餐日期
     */
    @ApiModelProperty(value = "就餐日期")
    private LocalDate dineDate;

    /**
     * 就餐方式(堂食/送餐)
     */
    @ApiModelProperty(value = "就餐方式(堂食/送餐)")
    private String dineType;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;

    /**
     * 订单状态 0待支付 1已完成
     */
    @ApiModelProperty(value = "订单状态 0待支付 1已完成")
    private Integer status;

    /**
     * 用餐状态 0未用餐 1已用餐打卡
     */
    @ApiModelProperty(value = "用餐状态 0未用餐 1已用餐打卡")
    private Integer dineFlag;
}
