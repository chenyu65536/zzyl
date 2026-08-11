package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 入库物资表(批次库存)
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "入库物资表")
public class WarehouseMaterial extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 入库登记编号
     */
    @ApiModelProperty(value = "入库登记编号")
    private Long warehouseRecordId;

    /**
     * 物资编号
     */
    @ApiModelProperty(value = "物资编号")
    private Long materialId;

    /**
     * 入库数量
     */
    @ApiModelProperty(value = "入库数量")
    private Integer warehouseNum;

    /**
     * 剩余库存量
     */
    @ApiModelProperty(value = "剩余库存量")
    private Integer inventory;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private LocalDate productDate;

    /**
     * 有效期至
     */
    @ApiModelProperty(value = "有效期至")
    private LocalDate expireDate;
}
