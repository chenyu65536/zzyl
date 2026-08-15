package com.zzyl.dto;

import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "RoomDto", description = "客房信息")
public class RoomDto extends BaseDto {

    /**
     * 客房编号
     */
    @ApiModelProperty(value = "客房编号")
    @NotBlank(message = "客房编号不能为空")
    @Size(max = 50, message = "客房编号不能超过50个字符")
    private String code;

    /**
     * 客房排序
     */
    @ApiModelProperty(value = "客房排序")
    private Integer sort;

    /**
     * 客房类型名称
     */
    @ApiModelProperty(value = "客房类型名称")
    private String typeName;

    /**
     * 楼层id
     */
    @ApiModelProperty(value = "楼层id")
    @NotNull(message = "楼层不能为空")
    private Long floorId;
}
