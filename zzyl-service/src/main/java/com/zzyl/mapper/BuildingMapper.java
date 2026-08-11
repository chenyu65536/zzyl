package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.Building;
import com.zzyl.vo.BuildingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BuildingMapper {

    int insert(Building building);

    int update(Building building);

    int deleteById(Long id);

    Building findById(Long id);

    Building findByName(@Param("name") String name);

    List<Building> findAll();

    IPage<BuildingVo> findByPage(Page<BuildingVo> page, @Param("name") String name);
}
