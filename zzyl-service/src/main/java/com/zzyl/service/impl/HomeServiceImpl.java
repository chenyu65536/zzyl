package com.zzyl.service.impl;

import com.zzyl.mapper.DashboardMapper;
import com.zzyl.service.HomeService;
import com.zzyl.vo.HomeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 机构看板服务实现(首页统计)
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class HomeServiceImpl implements HomeService {

    @Autowired
    private DashboardMapper dashboardMapper;

    /**
     * 机构看板总览
     */
    @Override
    public HomeVo overview() {
        HomeVo homeVo = new HomeVo();
        // 床位/房间/楼层
        Integer bedTotal = defaultZero(dashboardMapper.countBedTotal());
        Integer bedOccupied = defaultZero(dashboardMapper.countBedOccupied());
        homeVo.setBedTotal(bedTotal);
        homeVo.setBedOccupied(bedOccupied);
        homeVo.setBedFree(bedTotal - bedOccupied);
        homeVo.setRoomTotal(defaultZero(dashboardMapper.countRoomTotal()));
        homeVo.setFloorTotal(defaultZero(dashboardMapper.countFloorTotal()));
        // 在住老人及健康标识(红:有重大疾病;黄:有病史或过敏药物;绿:其余)
        Integer elderTotal = defaultZero(dashboardMapper.countElderInLive());
        Integer red = defaultZero(dashboardMapper.countElderHealthRed());
        Integer yellow = defaultZero(dashboardMapper.countElderHealthYellow());
        homeVo.setElderTotal(elderTotal);
        homeVo.setElderHealthRed(red);
        homeVo.setElderHealthYellow(yellow);
        homeVo.setElderHealthGreen(Math.max(elderTotal - red - yellow, 0));
        // 今日用餐情况
        homeVo.setTodayMealOrders(defaultZero(dashboardMapper.countTodayMealOrders()));
        homeVo.setTodayMealDined(defaultZero(dashboardMapper.countTodayMealDined()));
        // 今日起居/护理情况
        homeVo.setTodayNursingTotal(defaultZero(dashboardMapper.countTodayNursingTotal()));
        homeVo.setTodayNursingDone(defaultZero(dashboardMapper.countTodayNursingDone()));
        // IOT设备(电子工牌等)
        homeVo.setDeviceTotal(defaultZero(dashboardMapper.countDeviceTotal()));
        homeVo.setTodayAlarmTotal(defaultZero(dashboardMapper.countTodayAlarmTotal()));
        homeVo.setTodayAlarmUnprocessed(defaultZero(dashboardMapper.countTodayAlarmUnprocessed()));
        return homeVo;
    }

    private Integer defaultZero(Integer value) {
        return value == null ? 0 : value;
    }
}
