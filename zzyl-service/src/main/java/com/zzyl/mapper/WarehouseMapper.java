package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.Warehouse;
import com.zzyl.vo.WarehouseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarehouseMapper {

    int insert(Warehouse warehouse);

    int update(Warehouse warehouse);

    Warehouse findById(Long id);

    Warehouse findByName(@Param("name") String name);

    List<Warehouse> findAll();

    IPage<WarehouseVo> findByPage(Page<WarehouseVo> page, @Param("name") String name);
}
