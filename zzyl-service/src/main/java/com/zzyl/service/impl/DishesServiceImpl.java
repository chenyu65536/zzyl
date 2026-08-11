package com.zzyl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.base.PageResponse;
import com.zzyl.dto.DishesDto;
import com.zzyl.dto.DishesTypeDto;
import com.zzyl.entity.Dishes;
import com.zzyl.entity.DishesType;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.DishesMapper;
import com.zzyl.mapper.DishesTypeMapper;
import com.zzyl.service.DishesService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.DishesTypeVo;
import com.zzyl.vo.DishesVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜品管理服务实现(含菜品类别)
 */
@Slf4j
@Service
@Transactional
public class DishesServiceImpl implements DishesService {

    /**
     * 菜品类别数量上限
     */
    private static final int TYPE_MAX_TOTAL = 50;

    @Autowired
    private DishesTypeMapper dishesTypeMapper;

    @Autowired
    private DishesMapper dishesMapper;

    // ==================== 菜品类别 ====================

    /**
     * 新增菜品类别
     */
    @Override
    public void addType(DishesTypeDto dto) {
        if (ObjectUtil.isNotEmpty(dishesTypeMapper.findByName(dto.getName()))) {
            throw new BaseException("菜品类别名称已存在");
        }
        if (dishesTypeMapper.countAll() >= TYPE_MAX_TOTAL) {
            throw new BaseException("菜品类别数量已达上限");
        }
        DishesType dishesType = BeanUtil.toBean(dto, DishesType.class);
        dishesType.setCreateBy(UserThreadLocal.getMgtUserId());
        dishesType.setCreateTime(LocalDateTime.now());
        dishesTypeMapper.insert(dishesType);
    }

    /**
     * 更新菜品类别
     */
    @Override
    public void updateType(Long id, DishesTypeDto dto) {
        DishesType byName = dishesTypeMapper.findByName(dto.getName());
        if (ObjectUtil.isNotEmpty(byName) && !Objects.equals(byName.getId(), id)) {
            throw new BaseException("菜品类别名称已存在");
        }
        DishesType dishesType = BeanUtil.toBean(dto, DishesType.class);
        dishesType.setId(id);
        dishesType.setUpdateBy(UserThreadLocal.getMgtUserId());
        dishesType.setUpdateTime(LocalDateTime.now());
        dishesTypeMapper.update(dishesType);
    }

    /**
     * 删除菜品类别(类别下存在菜品时禁止删除)
     */
    @Override
    public void deleteTypeById(Long id) {
        List<Dishes> dishesList = dishesMapper.findByTypeId(id);
        if (ObjectUtil.isNotEmpty(dishesList)) {
            throw new BaseException("该类别下存在菜品,不能删除");
        }
        DishesType dishesType = new DishesType();
        dishesType.setId(id);
        dishesType.setDelFlag(1);
        dishesType.setUpdateBy(UserThreadLocal.getMgtUserId());
        dishesType.setUpdateTime(LocalDateTime.now());
        dishesTypeMapper.update(dishesType);
    }

    /**
     * 根据ID查询菜品类别
     */
    @Override
    public DishesTypeVo findTypeById(Long id) {
        DishesType dishesType = dishesTypeMapper.findById(id);
        if (ObjectUtil.isEmpty(dishesType)) {
            return null;
        }
        return BeanUtil.toBean(dishesType, DishesTypeVo.class);
    }

    /**
     * 查询所有菜品类别(下拉)
     */
    @Override
    public List<DishesTypeVo> findAllTypes(String name) {
        return dishesTypeMapper.findAll(name).stream()
                .map(dishesType -> BeanUtil.toBean(dishesType, DishesTypeVo.class))
                .collect(Collectors.toList());
    }

    // ==================== 菜品 ====================

    /**
     * 新增菜品
     */
    @Override
    public void add(DishesDto dto) {
        if (ObjectUtil.isNotEmpty(dishesMapper.findByTypeIdAndName(dto.getTypeId(), dto.getName()))) {
            throw new BaseException("该类别下菜品名称已存在");
        }
        Dishes dishes = BeanUtil.toBean(dto, Dishes.class);
        dishes.setCreateBy(UserThreadLocal.getMgtUserId());
        dishes.setCreateTime(LocalDateTime.now());
        dishesMapper.insert(dishes);
    }

    /**
     * 更新菜品
     */
    @Override
    public void update(Long id, DishesDto dto) {
        Dishes byName = dishesMapper.findByTypeIdAndName(dto.getTypeId(), dto.getName());
        if (ObjectUtil.isNotEmpty(byName) && !Objects.equals(byName.getId(), id)) {
            throw new BaseException("该类别下菜品名称已存在");
        }
        Dishes dishes = BeanUtil.toBean(dto, Dishes.class);
        dishes.setId(id);
        dishes.setUpdateBy(UserThreadLocal.getMgtUserId());
        dishes.setUpdateTime(LocalDateTime.now());
        dishesMapper.update(dishes);
    }

    /**
     * 删除菜品(逻辑删除)
     */
    @Override
    public void deleteById(Long id) {
        Dishes dishes = new Dishes();
        dishes.setId(id);
        dishes.setDelFlag(1);
        dishes.setUpdateBy(UserThreadLocal.getMgtUserId());
        dishes.setUpdateTime(LocalDateTime.now());
        dishesMapper.update(dishes);
    }

    /**
     * 根据ID查询菜品
     */
    @Override
    public DishesVo findById(Long id) {
        Dishes dishes = dishesMapper.findById(id);
        if (ObjectUtil.isEmpty(dishes)) {
            return null;
        }
        return BeanUtil.toBean(dishes, DishesVo.class);
    }

    /**
     * 分页查询菜品
     */
    @Override
    public PageResponse<DishesVo> findByPage(int pageNum, int pageSize, String name, Long typeId) {
        Page<DishesVo> page = new Page<>(pageNum, pageSize);
        IPage<DishesVo> byPage = dishesMapper.findByPage(page, name, typeId);
        return PageResponse.of(byPage, DishesVo.class);
    }
}
