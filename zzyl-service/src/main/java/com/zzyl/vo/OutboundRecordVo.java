package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 出库登记VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "出库登记VO")
public class OutboundRecordVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库编号")
    private Long warehouseId;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "经办人编号")
    private Long staffId;

    @ApiModelProperty(value = "经办人姓名")
    private String staffName;

    @ApiModelProperty(value = "领用人编号")
    private Long recipientId;

    @ApiModelProperty(value = "领用人类型 0员工 1老人")
    private Integer recipientType;

    @ApiModelProperty(value = "领用人姓名")
    private String recipientName;

    @ApiModelProperty(value = "物资去向")
    private String materialUse;

    @ApiModelProperty(value = "出库物资名称(逗号拼接)")
    private String materialNames;

    @ApiModelProperty(value = "出库时间")
    private LocalDateTime outboundTime;

    @ApiModelProperty(value = "出库状态 0待审核 1已通过 2未通过")
    private Integer status;

    @ApiModelProperty(value = "出库物资列表")
    private List<OutboundMaterialVo> materialList;
}
