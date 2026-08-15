package com.zzyl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 出库登记DTO
 */
@Data
@ApiModel(description = "出库登记DTO")
public class OutboundRecordDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "仓库编号", required = true)
    @NotNull(message = "仓库不能为空")
    private Long warehouseId;

    @ApiModelProperty(value = "经办人编号")
    private Long staffId;

    @ApiModelProperty(value = "领用人编号", required = true)
    @NotNull(message = "领用人不能为空")
    private Long recipientId;

    @ApiModelProperty(value = "领用人类型 0员工 1老人", required = true)
    @NotNull(message = "领用人类型不能为空")
    private Integer recipientType;

    @ApiModelProperty(value = "物资去向(护理用品领用/办公用品领用)", required = true)
    @NotBlank(message = "物资去向不能为空")
    private String materialUse;

    @ApiModelProperty(value = "出库时间")
    private LocalDateTime outboundTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "出库物资列表", required = true)
    @Valid
    @NotEmpty(message = "出库物资不能为空")
    private List<OutboundMaterialDto> materialList;

    /**
     * 出库物资DTO
     */
    @Data
    @ApiModel(description = "出库物资DTO")
    public static class OutboundMaterialDto implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "入库物资编号(批次)", required = true)
        @NotNull(message = "入库物资批次不能为空")
        private Long warehouseMaterialId;

        @ApiModelProperty(value = "出库数量", required = true)
        @NotNull(message = "出库数量不能为空")
        @Min(value = 1, message = "出库数量最小为1")
        private Integer outboundNum;
    }
}
