package com.zzyl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "订单信息")
public class OrderDto extends BaseDto {

    @ApiModelProperty(value = "支付状态")
    private Byte paymentStatus;

    @ApiModelProperty(value = "订单金额")
    @DecimalMin(value = "0", message = "订单金额不能为负数")
    private BigDecimal amount;

    @ApiModelProperty(value = "项目ID")
    @NotNull(message = "护理项目不能为空")
    private Long projectId;

    @ApiModelProperty(value = "老人ID")
    @NotNull(message = "老人不能为空")
    private Long elderId;

    @ApiModelProperty(value = "预计到达时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedArrivalTime;

}

