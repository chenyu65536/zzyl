package com.zzyl.service;

import com.zzyl.vo.HomeVo;

/**
 * 机构看板服务(首页统计)
 */
public interface HomeService {

    /**
     * 机构看板总览:床位/房间/在住老人(健康标识)/今日用餐/今日护理/IOT设备告警
     */
    HomeVo overview();
}
