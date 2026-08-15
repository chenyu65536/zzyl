package com.zzyl.dto;

import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class FloorDto extends BaseDto {

    /**
     * 楼层名称
     */
    @ApiModelProperty(value = "楼层名称")
    @NotBlank(message = "楼层名称不能为空")
    @Size(max = 50, message = "楼层名称不能超过50个字符")
    private String name;

    /**
     * 楼层编号
     */
    @ApiModelProperty(value = "楼层编号")
    @NotNull(message = "楼层编号不能为空")
    private Integer code;
}
