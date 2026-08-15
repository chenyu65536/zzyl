package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author itheima
 */
@Data
@ApiModel("退款凭证DTO")
public class RefundVoucherDto {

    /**
     * 退款渠道【支付宝、微信、现金】
     */
    @ApiModelProperty(value = "退款渠道【支付宝、微信、现金】")
    @NotBlank(message = "退款渠道不能为空")
    private String tradingChannel;

    /**
     * 退款凭证URL
     */
    @ApiModelProperty(value = "退款凭证URL")
    private String refundVoucherUrl;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 退住单编码
     */
    @ApiModelProperty(value = "退住单编码")
    @NotBlank(message = "退住单编码不能为空")
    private String retreatCode;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    @NotNull(message = "退款金额不能为空")
    @DecimalMin(value = "0", message = "退款金额不能为负数")
    private BigDecimal refundAmount;

}
