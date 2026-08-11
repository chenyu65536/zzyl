package com.zzyl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.base.PageResponse;
import com.zzyl.dto.StaffDto;
import com.zzyl.entity.Staff;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.StaffMapper;
import com.zzyl.service.StaffService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.StaffVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 员工管理服务实现
 */
@Slf4j
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    /**
     * 新增员工
     */
    @Override
    public void add(StaffDto dto) {
        // 校验手机号是否已存在
        if (ObjectUtil.isNotEmpty(staffMapper.findByPhone(dto.getPhone()))) {
            throw new BaseException("手机号已存在");
        }
        // 邮箱非空时校验邮箱是否已存在
        if (ObjectUtil.isNotEmpty(dto.getEmail())
                && ObjectUtil.isNotEmpty(staffMapper.findByEmail(dto.getEmail()))) {
            throw new BaseException("邮箱已存在");
        }
        Staff staff = BeanUtil.toBean(dto, Staff.class);
        staff.setCreateBy(UserThreadLocal.getMgtUserId());
        staff.setCreateTime(LocalDateTime.now());
        staffMapper.insert(staff);
    }

    /**
     * 更新员工
     */
    @Override
    public void update(Long id, StaffDto dto) {
        Staff exist = staffMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("员工不存在");
        }
        // 校验手机号是否与其他员工重复
        Staff byPhone = staffMapper.findByPhone(dto.getPhone());
        if (ObjectUtil.isNotEmpty(byPhone) && !Objects.equals(byPhone.getId(), id)) {
            throw new BaseException("手机号已存在");
        }
        // 邮箱非空时校验邮箱是否与其他员工重复
        if (ObjectUtil.isNotEmpty(dto.getEmail())) {
            Staff byEmail = staffMapper.findByEmail(dto.getEmail());
            if (ObjectUtil.isNotEmpty(byEmail) && !Objects.equals(byEmail.getId(), id)) {
                throw new BaseException("邮箱已存在");
            }
        }
        Staff staff = BeanUtil.toBean(dto, Staff.class);
        staff.setId(id);
        staff.setUpdateBy(UserThreadLocal.getMgtUserId());
        staff.setUpdateTime(LocalDateTime.now());
        staffMapper.update(staff);
    }

    /**
     * 员工离职
     */
    @Override
    public void leave(Long id) {
        Staff exist = staffMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("员工不存在");
        }
        Staff staff = new Staff();
        staff.setId(id);
        staff.setLeaveFlag("1");
        staff.setUpdateBy(UserThreadLocal.getMgtUserId());
        staff.setUpdateTime(LocalDateTime.now());
        staffMapper.update(staff);
    }

    /**
     * 根据ID查询员工
     */
    @Override
    public StaffVo findById(Long id) {
        Staff staff = staffMapper.findById(id);
        if (ObjectUtil.isEmpty(staff)) {
            return null;
        }
        return BeanUtil.toBean(staff, StaffVo.class);
    }

    /**
     * 查询在职员工(下拉)
     */
    @Override
    public List<StaffVo> findAll(String name) {
        return staffMapper.findAll(name).stream()
                .map(staff -> BeanUtil.toBean(staff, StaffVo.class))
                .collect(Collectors.toList());
    }

    /**
     * 分页查询员工
     */
    @Override
    public PageResponse<StaffVo> findByPage(int pageNum, int pageSize, String name, String phone, String leaveFlag) {
        Page<StaffVo> page = new Page<>(pageNum, pageSize);
        IPage<StaffVo> byPage = staffMapper.findByPage(page, name, phone, leaveFlag);
        return PageResponse.of(byPage, StaffVo.class);
    }
}
