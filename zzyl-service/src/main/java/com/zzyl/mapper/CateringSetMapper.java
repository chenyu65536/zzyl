package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.CateringSet;
import com.zzyl.vo.CateringSetVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CateringSetMapper {

    int insert(CateringSet cateringSet);

    int update(CateringSet cateringSet);

    CateringSet findById(Long id);

    CateringSet findByName(@Param("name") String name);

    IPage<CateringSetVo> findByPage(Page<CateringSetVo> page, @Param("name") String name);
}
