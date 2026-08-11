package com.zzyl.mapper;

import com.zzyl.entity.MealOrderDishes;
import com.zzyl.vo.MealOrderDishesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MealOrderDishesMapper {

    int insert(MealOrderDishes mealOrderDishes);

    List<MealOrderDishesVo> findByMealOrderId(@Param("mealOrderId") Long mealOrderId);
}
