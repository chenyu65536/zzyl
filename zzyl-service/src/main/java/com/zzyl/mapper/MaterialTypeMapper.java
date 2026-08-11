package com.zzyl.mapper;

import com.zzyl.entity.MaterialType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialTypeMapper {

    int insert(MaterialType materialType);

    int update(MaterialType materialType);

    MaterialType findById(Long id);

    MaterialType findByName(@Param("name") String name);

    List<MaterialType> findAll(@Param("name") String name);

    int countAll();
}
