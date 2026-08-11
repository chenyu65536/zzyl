package com.zzyl.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 机构看板VO(首页统计)
 */
@Data
@ApiModel(description = "机构看板VO")
public class HomeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    // ==================== 床位/房间 ====================

    @ApiModelProperty(value = "床位总数")
    private Integer bedTotal;

    @ApiModelProperty(value = "已入住床位数")
    private Integer bedOccupied;

    @ApiModelProperty(value = "空闲床位数")
    private Integer bedFree;

    @ApiModelProperty(value = "房间总数")
    private Integer roomTotal;

    @ApiModelProperty(value = "楼层总数")
    private Integer floorTotal;

    // ==================== 入住老人 ====================

    @ApiModelProperty(value = "在住老人数(已分配床位)")
    private Integer elderTotal;

    @ApiModelProperty(value = "健康标识-绿(无重大疾病及病史)")
    private Integer elderHealthGreen;

    @ApiModelProperty(value = "健康标识-黄(有病史或过敏药物)")
    private Integer elderHealthYellow;

    @ApiModelProperty(value = "健康标识-红(有重大疾病)")
    private Integer elderHealthRed;

    // ==================== 今日用餐情况 ====================

    @ApiModelProperty(value = "今日订餐数")
    private Integer todayMealOrders;

    @ApiModelProperty(value = "今日已用餐数(打卡)")
    private Integer todayMealDined;

    // ==================== 今日起居/护理情况 ====================

    @ApiModelProperty(value = "今日护理任务总数")
    private Integer todayNursingTotal;

    @ApiModelProperty(value = "今日已完成护理任务数")
    private Integer todayNursingDone;

    // ==================== IOT设备(电子工牌等) ====================

    @ApiModelProperty(value = "接入设备总数")
    private Integer deviceTotal;

    @ApiModelProperty(value = "今日设备告警数")
    private Integer todayAlarmTotal;

    @ApiModelProperty(value = "今日未处理告警数")
    private Integer todayAlarmUnprocessed;
}
