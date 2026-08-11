package com.zzyl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.base.PageResponse;
import com.zzyl.dto.CateringSetDto;
import com.zzyl.entity.CateringSet;
import com.zzyl.entity.SetDishes;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.CateringSetMapper;
import com.zzyl.mapper.DishesMapper;
import com.zzyl.mapper.SetDishesMapper;
import com.zzyl.service.CateringSetService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.CateringSetVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 餐饮套餐服务实现
 */
@Slf4j
@Service
@Transactional
public class CateringSetServiceImpl implements CateringSetService {

    @Autowired
    private CateringSetMapper cateringSetMapper;

    @Autowired
    private SetDishesMapper setDishesMapper;

    @Autowired
    private DishesMapper dishesMapper;

    /**
     * 新增套餐(含套餐菜品关联)
     */
    @Override
    public void add(CateringSetDto dto) {
        if (ObjectUtil.isNotEmpty(cateringSetMapper.findByName(dto.getName()))) {
            throw new BaseException("套餐名称已存在");
        }
        if (ObjectUtil.isEmpty(dto.getDishesIdList())) {
            throw new BaseException("套餐内菜品不能为空");
        }
        Long userId = UserThreadLocal.getMgtUserId();
        LocalDateTime now = LocalDateTime.now();
        CateringSet cateringSet = BeanUtil.toBean(dto, CateringSet.class);
        cateringSet.setCreateBy(userId);
        cateringSet.setCreateTime(now);
        cateringSetMapper.insert(cateringSet);
        saveSetDishes(cateringSet.getId(), dto.getDishesIdList(), userId, now);
    }

    /**
     * 更新套餐(重建套餐菜品关联)
     */
    @Override
    public void update(Long id, CateringSetDto dto) {
        if (ObjectUtil.isEmpty(cateringSetMapper.findById(id))) {
            throw new BaseException("套餐不存在");
        }
        CateringSet byName = cateringSetMapper.findByName(dto.getName());
        if (ObjectUtil.isNotEmpty(byName) && !Objects.equals(byName.getId(), id)) {
            throw new BaseException("套餐名称已存在");
        }
        if (ObjectUtil.isEmpty(dto.getDishesIdList())) {
            throw new BaseException("套餐内菜品不能为空");
        }
        Long userId = UserThreadLocal.getMgtUserId();
        LocalDateTime now = LocalDateTime.now();
        CateringSet cateringSet = BeanUtil.toBean(dto, CateringSet.class);
        cateringSet.setId(id);
        cateringSet.setUpdateBy(userId);
        cateringSet.setUpdateTime(now);
        cateringSetMapper.update(cateringSet);
        // 先删除原有菜品关联,再重新批量插入
        setDishesMapper.deleteBySetId(id);
        saveSetDishes(id, dto.getDishesIdList(), userId, now);
    }

    /**
     * 删除套餐(逻辑删除)
     */
    @Override
    public void deleteById(Long id) {
        CateringSet cateringSet = cateringSetMapper.findById(id);
        if (ObjectUtil.isEmpty(cateringSet)) {
            throw new BaseException("套餐不存在");
        }
        CateringSet update = new CateringSet();
        update.setId(id);
        update.setDelFlag(1);
        update.setUpdateBy(UserThreadLocal.getMgtUserId());
        update.setUpdateTime(LocalDateTime.now());
        cateringSetMapper.update(update);
    }

    /**
     * 根据ID查询套餐详情(含套餐内菜品列表)
     */
    @Override
    public CateringSetVo findById(Long id) {
        CateringSet cateringSet = cateringSetMapper.findById(id);
        if (ObjectUtil.isEmpty(cateringSet)) {
            throw new BaseException("套餐不存在");
        }
        CateringSetVo vo = BeanUtil.toBean(cateringSet, CateringSetVo.class);
        vo.setDishesList(dishesMapper.findBySetId(id));
        return vo;
    }

    /**
     * 分页查询套餐
     */
    @Override
    public PageResponse<CateringSetVo> findByPage(int pageNum, int pageSize, String name) {
        Page<CateringSetVo> page = new Page<>(pageNum, pageSize);
        IPage<CateringSetVo> byPage = cateringSetMapper.findByPage(page, name);
        return PageResponse.of(byPage, CateringSetVo.class);
    }

    /**
     * 批量保存套餐菜品关联(菜品编号去重并校验存在)
     */
    private void saveSetDishes(Long setId, List<Long> dishesIdList, Long userId, LocalDateTime now) {
        List<Long> dishesIds = dishesIdList.stream().distinct().collect(Collectors.toList());
        if (dishesMapper.findByIds(dishesIds).size() != dishesIds.size()) {
            throw new BaseException("套餐内存在无效菜品");
        }
        dishesIds.forEach(dishesId -> {
            SetDishes setDishes = new SetDishes();
            setDishes.setSetId(setId);
            setDishes.setDishesId(dishesId);
            setDishes.setCreateBy(userId);
            setDishes.setCreateTime(now);
            setDishesMapper.insert(setDishes);
        });
    }
}
