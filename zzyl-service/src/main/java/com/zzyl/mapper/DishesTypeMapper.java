package com.zzyl.mapper;

import com.zzyl.entity.DishesType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishesTypeMapper {

    int insert(DishesType dishesType);

    int update(DishesType dishesType);

    DishesType findById(Long id);

    DishesType findByName(@Param("name") String name);

    List<DishesType> findAll(@Param("name") String name);

    int countAll();
}
