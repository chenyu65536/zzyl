package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.Visit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface VisitMapper {

    int insert(Visit visit);

    int update(Visit visit);

    int deleteById(Long id);

    Visit findById(Long id);

    List<Visit> findAll(@Param("mobile") String mobile, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    IPage<Visit> findByPage(Page<Visit> page, @Param("page") int startIndex, @Param("pageSize") int pageSize, @Param("name") String name, @Param("mobile") String mobile, @Param("status") Integer status, @Param("type") Integer type, @Param("createBy") Long userId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
