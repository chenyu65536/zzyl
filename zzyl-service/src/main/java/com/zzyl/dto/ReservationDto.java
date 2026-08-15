package com.zzyl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ReservationDto extends BaseDto {

    /**
     * 预约人
     */
    @ApiModelProperty("预约人")
    @NotBlank(message = "预约人姓名不能为空")
    @Size(max = 50, message = "预约人姓名不能超过50个字符")
    private String name;

    /**
     * 预约人手机号
     */
    @ApiModelProperty("预约人手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String mobile;

    /**
     * 时间
     */
    @ApiModelProperty("时间")
    @NotNull(message = "预约时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    /**
     * 探访人
     */
    @ApiModelProperty("探访人")
    @Size(max = 50, message = "探访人姓名不能超过50个字符")
    private String visitor;

    /**
     * 预约类型，0：参观预约，1：探访预约
     */
    @ApiModelProperty("预约类型，0：参观预约，1：探访预约")
    @NotNull(message = "预约类型不能为空")
    private Integer type;

    /**
     * 预约状态，0：待报道，1：已完成，2：取消，3：过期
     */
    @ApiModelProperty("预约状态，0：待报道，1：已完成，2：取消，3：过期")
    private Integer status;


    private Long elderId;
}
