package com.zzyl.mapper;

import com.zzyl.entity.SetDishes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SetDishesMapper {

    int insert(SetDishes setDishes);

    int deleteBySetId(@Param("setId") Long setId);

    List<SetDishes> findBySetId(@Param("setId") Long setId);
}
