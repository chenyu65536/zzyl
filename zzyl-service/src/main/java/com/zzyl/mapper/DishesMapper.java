package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.Dishes;
import com.zzyl.vo.DishesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishesMapper {

    int insert(Dishes dishes);

    int update(Dishes dishes);

    Dishes findById(Long id);

    Dishes findByTypeIdAndName(@Param("typeId") Long typeId, @Param("name") String name);

    List<Dishes> findByTypeId(@Param("typeId") Long typeId);

    List<Dishes> findByIds(@Param("ids") List<Long> ids);

    IPage<DishesVo> findByPage(Page<DishesVo> page, @Param("name") String name, @Param("typeId") Long typeId);

    List<DishesVo> findBySetId(@Param("setId") Long setId);
}
