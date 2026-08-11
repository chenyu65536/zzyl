package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库登记VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "入库登记VO")
public class WarehouseRecordVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库编号")
    private Long warehouseId;

    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "经办人编号")
    private Long staffId;

    @ApiModelProperty(value = "经办人姓名")
    private String staffName;

    @ApiModelProperty(value = "物资来源")
    private String source;

    @ApiModelProperty(value = "入库物资名称(逗号拼接)")
    private String materialNames;

    @ApiModelProperty(value = "入库时间")
    private LocalDateTime warehouseTime;

    @ApiModelProperty(value = "入库状态 0待审核 1已通过 2未通过")
    private Integer status;

    @ApiModelProperty(value = "入库物资列表")
    private List<WarehouseMaterialVo> materialList;
}
