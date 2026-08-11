package com.zzyl.service;

import com.zzyl.base.PageResponse;
import com.zzyl.dto.StaffDto;
import com.zzyl.vo.StaffVo;

import java.util.List;

/**
 * 员工管理服务
 */
public interface StaffService {

    void add(StaffDto dto);

    void update(Long id, StaffDto dto);

    void leave(Long id);

    StaffVo findById(Long id);

    List<StaffVo> findAll(String name);

    PageResponse<StaffVo> findByPage(int pageNum, int pageSize, String name, String phone, String leaveFlag);
}
