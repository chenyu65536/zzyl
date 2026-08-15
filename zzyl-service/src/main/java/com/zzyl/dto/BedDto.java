package com.zzyl.dto;

import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel("床位实体类")
public class BedDto extends BaseDto {
    /**
     * 床位编号
     */
    @ApiModelProperty("床位编号")
    @NotBlank(message = "床位编号不能为空")
    @Size(max = 50, message = "床位编号不能超过50个字符")
    private String bedNumber;

    /**
     * 床位状态: 未入住0, 已入住1
     */
    @ApiModelProperty(value = "床位状态: 未入住0, 已入住1",example = "0")
    private Integer bedStatus;

    /**
     * 房间ID
     */
    @ApiModelProperty("房间ID")
    @NotNull(message = "房间ID不能为空")
    private Long roomId;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer sort;
}
