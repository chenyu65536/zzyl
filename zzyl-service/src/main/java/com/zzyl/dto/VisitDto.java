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
public class VisitDto extends BaseDto {

    @ApiModelProperty("来访人")
    @NotBlank(message = "来访人姓名不能为空")
    @Size(max = 50, message = "来访人姓名不能超过50个字符")
    private String name;

    @ApiModelProperty("来访人手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String mobile;

    @ApiModelProperty("时间")
    @NotNull(message = "来访时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    @ApiModelProperty("探访人")
    @Size(max = 50, message = "探访人姓名不能超过50个字符")
    private String visitor;

    @ApiModelProperty("来访类型，0：参观来访，1：探访来访")
    @NotNull(message = "来访类型不能为空")
    private Integer type;

    @ApiModelProperty("来访状态，0：待报道，1：已完成，2：取消，3：过期")
    private Integer status;

}
