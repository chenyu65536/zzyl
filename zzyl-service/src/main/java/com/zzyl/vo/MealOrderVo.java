package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订餐VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "订餐VO")
public class MealOrderVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号")
    private Long elderId;

    @ApiModelProperty(value = "老人姓名")
    private String elderName;

    @ApiModelProperty(value = "套餐编号")
    private Long cateringSetId;

    @ApiModelProperty(value = "套餐名称")
    private String cateringSetName;

    @ApiModelProperty(value = "送餐人编号")
    private Long staffId;

    @ApiModelProperty(value = "送餐人姓名")
    private String staffName;

    @ApiModelProperty(value = "送餐时间")
    private LocalDateTime deliverTime;

    @ApiModelProperty(value = "就餐日期")
    private LocalDate dineDate;

    @ApiModelProperty(value = "就餐方式(堂食/送餐)")
    private String dineType;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "订单状态 0待支付 1已完成")
    private Integer status;

    @ApiModelProperty(value = "用餐状态 0未用餐 1已用餐打卡")
    private Integer dineFlag;

    @ApiModelProperty(value = "订餐菜品名称(逗号拼接)")
    private String dishesNames;

    @ApiModelProperty(value = "订餐菜品列表")
    private List<MealOrderDishesVo> dishesList;
}
