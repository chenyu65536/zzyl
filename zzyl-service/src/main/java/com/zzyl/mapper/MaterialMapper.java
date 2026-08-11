package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.Material;
import com.zzyl.vo.MaterialVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MaterialMapper {

    int insert(Material material);

    int update(Material material);

    Material findById(Long id);

    Material findByTypeIdAndName(@Param("typeId") Long typeId, @Param("name") String name);

    List<Material> findByTypeId(@Param("typeId") Long typeId);

    List<Material> findAll(@Param("name") String name);

    IPage<MaterialVo> findByPage(Page<MaterialVo> page, @Param("name") String name, @Param("typeId") Long typeId);
}
