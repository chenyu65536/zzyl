package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订餐DTO
 */
@Data
@ApiModel(description = "订餐DTO")
public class MealOrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "老人编号", required = true)
    @NotNull(message = "老人不能为空")
    private Long elderId;

    @ApiModelProperty(value = "套餐编号(下单选择的套餐,可为空)")
    private Long cateringSetId;

    @ApiModelProperty(value = "送餐人编号")
    private Long staffId;

    @ApiModelProperty(value = "送餐时间")
    private LocalDateTime deliverTime;

    @ApiModelProperty(value = "就餐日期", required = true)
    @NotNull(message = "就餐日期不能为空")
    private LocalDate dineDate;

    @ApiModelProperty(value = "就餐方式(堂食/送餐)", required = true)
    @NotBlank(message = "就餐方式不能为空")
    private String dineType;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "订餐菜品列表", required = true)
    @Valid
    @NotEmpty(message = "订餐菜品不能为空")
    private List<OrderDishesDto> orderDishesList;

    /**
     * 订餐菜品DTO
     */
    @Data
    @ApiModel(description = "订餐菜品DTO")
    public static class OrderDishesDto implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "菜品编号", required = true)
        @NotNull(message = "菜品编号不能为空")
        private Long dishesId;

        @ApiModelProperty(value = "份数", required = true)
        @NotNull(message = "份数不能为空")
        @Min(value = 1, message = "份数最小为1")
        private Integer orderNum;
    }
}
