package com.zzyl.dto;

import com.aliyun.iot20180120.models.RegisterDeviceRequest;
import com.aliyun.tea.NameInMap;
import com.zzyl.base.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class DeviceDto extends BaseDto {

    @ApiModelProperty(value = "注册参数")
    RegisterDeviceRequest registerDeviceRequest;

    @NameInMap("IotId")
    public String iotId;

    @NameInMap("Nickname")
    public String nickname;

    @NameInMap("ProductKey")
    public String productKey;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "位置名称回显字段")
    private String deviceDescription;

    @ApiModelProperty(value = "位置类型")
    Integer locationType;

    @ApiModelProperty(value = "绑定位置")
    Long bindingLocation;

    @ApiModelProperty(value = "设备名称")
    @NotBlank(message = "设备名称不能为空")
    @Size(max = 100, message = "设备名称不能超过100个字符")
    String deviceName;

    @ApiModelProperty(value = "物理位置类型")
    Integer physicalLocationType;

    @ApiModelProperty(value = "设备ID")
    String deviceId;
}
