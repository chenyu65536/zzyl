package com.zzyl.service;

import com.zzyl.base.PageResponse;
import com.zzyl.dto.MealOrderDto;
import com.zzyl.vo.MealOrderVo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 订餐服务
 */
public interface MealOrderService {

    /**
     * 新增订餐(含菜品明细,套餐内菜品减免一份价格)
     */
    void add(MealOrderDto dto);

    /**
     * 送餐完成
     *
     * @param id          订餐编号
     * @param staffId     送餐人编号
     * @param deliverTime 送餐时间
     */
    void send(Long id, Long staffId, LocalDateTime deliverTime);

    /**
     * 用餐打卡
     */
    void dine(Long id);

    /**
     * 根据ID查询订餐详情(含菜品明细)
     */
    MealOrderVo findById(Long id);

    /**
     * 分页查询订餐
     */
    PageResponse<MealOrderVo> findByPage(int pageNum, int pageSize, String elderName, Integer status,
                                         LocalDate startDate, LocalDate endDate);
}
