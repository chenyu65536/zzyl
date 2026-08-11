package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.MealOrder;
import com.zzyl.vo.MealOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

@Mapper
public interface MealOrderMapper {

    int insert(MealOrder mealOrder);

    int update(MealOrder mealOrder);

    MealOrder findById(Long id);

    MealOrderVo findVoById(Long id);

    IPage<MealOrderVo> findByPage(Page<MealOrderVo> page,
                                  @Param("elderName") String elderName,
                                  @Param("status") Integer status,
                                  @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate);

    int countTodayOrders();

    int countTodayDined();
}
