package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.Staff;
import com.zzyl.vo.StaffVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StaffMapper {

    int insert(Staff staff);

    int update(Staff staff);

    Staff findById(Long id);

    Staff findByPhone(@Param("phone") String phone);

    Staff findByEmail(@Param("email") String email);

    List<Staff> findAll(@Param("name") String name);

    IPage<StaffVo> findByPage(Page<StaffVo> page, @Param("name") String name,
                              @Param("phone") String phone, @Param("leaveFlag") String leaveFlag);
}
