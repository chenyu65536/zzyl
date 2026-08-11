package com.zzyl.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 机构看板统计Mapper
 */
@Mapper
public interface DashboardMapper {

    /**
     * 床位总数
     */
    Integer countBedTotal();

    /**
     * 已入住床位数
     */
    Integer countBedOccupied();

    /**
     * 房间总数
     */
    Integer countRoomTotal();

    /**
     * 楼层总数
     */
    Integer countFloorTotal();

    /**
     * 在住老人数(已分配床位)
     */
    Integer countElderInLive();

    /**
     * 健康标识-红:有重大疾病的在住老人数
     */
    Integer countElderHealthRed();

    /**
     * 健康标识-黄:无重大疾病但有病史或过敏药物的在住老人数
     */
    Integer countElderHealthYellow();

    /**
     * 今日订餐数
     */
    Integer countTodayMealOrders();

    /**
     * 今日已用餐数(打卡)
     */
    Integer countTodayMealDined();

    /**
     * 今日护理任务总数
     */
    Integer countTodayNursingTotal();

    /**
     * 今日已完成护理任务数
     */
    Integer countTodayNursingDone();

    /**
     * 接入设备总数
     */
    Integer countDeviceTotal();

    /**
     * 今日设备告警数
     */
    Integer countTodayAlarmTotal();

    /**
     * 今日未处理告警数
     */
    Integer countTodayAlarmUnprocessed();
}
