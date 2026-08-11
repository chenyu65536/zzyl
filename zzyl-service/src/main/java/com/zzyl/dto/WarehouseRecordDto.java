package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 入库登记DTO
 */
@Data
@ApiModel(description = "入库登记DTO")
public class WarehouseRecordDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库编号", required = true)
    private Long warehouseId;

    @ApiModelProperty(value = "经办人编号")
    private Long staffId;

    @ApiModelProperty(value = "物资来源(采购入库/捐赠入库)", required = true)
    private String source;

    @ApiModelProperty(value = "入库时间")
    private LocalDateTime warehouseTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "入库物资列表", required = true)
    private List<WarehouseMaterialDto> materialList;

    /**
     * 入库物资DTO
     */
    @Data
    @ApiModel(description = "入库物资DTO")
    public static class WarehouseMaterialDto implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "物资编号", required = true)
        private Long materialId;

        @ApiModelProperty(value = "入库数量", required = true)
        private Integer warehouseNum;

        @ApiModelProperty(value = "生产日期")
        private LocalDate productDate;

        @ApiModelProperty(value = "有效期至")
        private LocalDate expireDate;
    }
}
