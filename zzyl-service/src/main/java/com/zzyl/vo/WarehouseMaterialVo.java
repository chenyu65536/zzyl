package com.zzyl.vo;

import com.zzyl.base.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 入库物资VO(批次库存)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "入库物资VO")
public class WarehouseMaterialVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "入库登记编号")
    private Long warehouseRecordId;

    @ApiModelProperty(value = "物资编号")
    private Long materialId;

    @ApiModelProperty(value = "物资名称")
    private String materialName;

    @ApiModelProperty(value = "物资类别名称")
    private String typeName;

    @ApiModelProperty(value = "物资规格")
    private String spec;

    @ApiModelProperty(value = "物资单价")
    private BigDecimal price;

    @ApiModelProperty(value = "所属仓库名称")
    private String warehouseName;

    @ApiModelProperty(value = "入库数量")
    private Integer warehouseNum;

    @ApiModelProperty(value = "剩余库存量")
    private Integer inventory;

    @ApiModelProperty(value = "生产日期")
    private LocalDate productDate;

    @ApiModelProperty(value = "有效期至")
    private LocalDate expireDate;
}
