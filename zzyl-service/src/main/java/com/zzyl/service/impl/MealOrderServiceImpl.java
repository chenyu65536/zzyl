package com.zzyl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.base.PageResponse;
import com.zzyl.dto.MealOrderDto;
import com.zzyl.entity.Dishes;
import com.zzyl.entity.MealOrder;
import com.zzyl.entity.MealOrderDishes;
import com.zzyl.entity.SetDishes;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.CateringSetMapper;
import com.zzyl.mapper.DishesMapper;
import com.zzyl.mapper.MealOrderDishesMapper;
import com.zzyl.mapper.MealOrderMapper;
import com.zzyl.mapper.SetDishesMapper;
import com.zzyl.service.MealOrderService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.MealOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 订餐服务实现
 */
@Slf4j
@Service
@Transactional
public class MealOrderServiceImpl implements MealOrderService {

    /**
     * 订单状态:待支付
     */
    public static final int STATUS_STAY_PAY = 0;
    /**
     * 订单状态:已完成
     */
    public static final int STATUS_FINISHED = 1;

    /**
     * 用餐状态:未用餐
     */
    public static final int DINE_FLAG_NOT = 0;
    /**
     * 用餐状态:已用餐打卡
     */
    public static final int DINE_FLAG_DINED = 1;

    /**
     * 套餐内菜品标识:否
     */
    public static final int SET_FLAG_NOT = 0;
    /**
     * 套餐内菜品标识:是
     */
    public static final int SET_FLAG_IN = 1;

    @Autowired
    private MealOrderMapper mealOrderMapper;

    @Autowired
    private MealOrderDishesMapper mealOrderDishesMapper;

    @Autowired
    private DishesMapper dishesMapper;

    @Autowired
    private CateringSetMapper cateringSetMapper;

    @Autowired
    private SetDishesMapper setDishesMapper;

    /**
     * 新增订餐(含菜品明细,套餐内菜品减免一份价格)
     */
    @Override
    public void add(MealOrderDto dto) {
        if (ObjectUtil.isEmpty(dto.getOrderDishesList())) {
            throw new BaseException("订餐菜品不能为空");
        }
        // 同一菜品多条明细按菜品编号合并份数
        Map<Long, Integer> orderNumMap = dto.getOrderDishesList().stream()
                .collect(Collectors.groupingBy(MealOrderDto.OrderDishesDto::getDishesId,
                        Collectors.summingInt(MealOrderDto.OrderDishesDto::getOrderNum)));
        orderNumMap.values().forEach(orderNum -> {
            if (orderNum <= 0) {
                throw new BaseException("订餐份数必须大于0");
            }
        });
        // 批量查询菜品
        Map<Long, Dishes> dishesMap = dishesMapper.findByIds(new ArrayList<>(orderNumMap.keySet())).stream()
                .collect(Collectors.toMap(Dishes::getId, Function.identity()));
        // 查询下单套餐内的菜品编号集合(套餐内菜品减免一份价格)
        Set<Long> setDishesIds = findSetDishesIds(dto.getCateringSetId());
        Long userId = UserThreadLocal.getMgtUserId();
        LocalDateTime now = LocalDateTime.now();
        // 计算菜品明细金额
        BigDecimal payAmount = BigDecimal.ZERO;
        List<MealOrderDishes> orderDishesList = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : orderNumMap.entrySet()) {
            Dishes dishes = dishesMap.get(entry.getKey());
            if (ObjectUtil.isEmpty(dishes)) {
                throw new BaseException("订餐菜品不存在");
            }
            Integer orderNum = entry.getValue();
            BigDecimal totalAmount = dishes.getPrice().multiply(BigDecimal.valueOf(orderNum));
            BigDecimal reallyAmount = totalAmount;
            int setFlag = SET_FLAG_NOT;
            if (setDishesIds.contains(dishes.getId())) {
                setFlag = SET_FLAG_IN;
                reallyAmount = totalAmount.subtract(dishes.getPrice());
            }
            MealOrderDishes mealOrderDishes = new MealOrderDishes();
            mealOrderDishes.setDishesId(dishes.getId());
            mealOrderDishes.setDishesName(dishes.getName());
            mealOrderDishes.setDishesPrice(dishes.getPrice());
            mealOrderDishes.setOrderNum(orderNum);
            mealOrderDishes.setSetFlag(setFlag);
            mealOrderDishes.setTotalAmount(totalAmount);
            mealOrderDishes.setReallyAmount(reallyAmount);
            mealOrderDishes.setCreateBy(userId);
            mealOrderDishes.setCreateTime(now);
            orderDishesList.add(mealOrderDishes);
            payAmount = payAmount.add(reallyAmount);
        }
        // 保存订餐,状态为待支付、未用餐
        MealOrder mealOrder = BeanUtil.toBean(dto, MealOrder.class);
        mealOrder.setPayAmount(payAmount);
        mealOrder.setStatus(STATUS_STAY_PAY);
        mealOrder.setDineFlag(DINE_FLAG_NOT);
        mealOrder.setCreateBy(userId);
        mealOrder.setCreateTime(now);
        mealOrderMapper.insert(mealOrder);
        // 保存订餐菜品明细
        orderDishesList.forEach(mealOrderDishes -> {
            mealOrderDishes.setMealOrderId(mealOrder.getId());
            mealOrderDishesMapper.insert(mealOrderDishes);
        });
    }

    /**
     * 送餐完成
     */
    @Override
    public void send(Long id, Long staffId, LocalDateTime deliverTime) {
        MealOrder mealOrder = mealOrderMapper.findById(id);
        if (ObjectUtil.isEmpty(mealOrder)) {
            throw new BaseException("订餐不存在");
        }
        if (!Objects.equals(mealOrder.getStatus(), STATUS_STAY_PAY)) {
            throw new BaseException("订餐已完成,不能重复送餐");
        }
        MealOrder update = new MealOrder();
        update.setId(id);
        update.setStaffId(staffId);
        update.setDeliverTime(ObjectUtil.isEmpty(deliverTime) ? LocalDateTime.now() : deliverTime);
        update.setStatus(STATUS_FINISHED);
        update.setUpdateBy(UserThreadLocal.getMgtUserId());
        update.setUpdateTime(LocalDateTime.now());
        mealOrderMapper.update(update);
    }

    /**
     * 用餐打卡
     */
    @Override
    public void dine(Long id) {
        MealOrder mealOrder = mealOrderMapper.findById(id);
        if (ObjectUtil.isEmpty(mealOrder)) {
            throw new BaseException("订餐不存在");
        }
        MealOrder update = new MealOrder();
        update.setId(id);
        update.setDineFlag(DINE_FLAG_DINED);
        update.setUpdateBy(UserThreadLocal.getMgtUserId());
        update.setUpdateTime(LocalDateTime.now());
        mealOrderMapper.update(update);
    }

    /**
     * 根据ID查询订餐详情(含菜品明细)
     */
    @Override
    public MealOrderVo findById(Long id) {
        MealOrderVo vo = mealOrderMapper.findVoById(id);
        if (ObjectUtil.isEmpty(vo)) {
            throw new BaseException("订餐不存在");
        }
        vo.setDishesList(mealOrderDishesMapper.findByMealOrderId(id));
        return vo;
    }

    /**
     * 分页查询订餐
     */
    @Override
    public PageResponse<MealOrderVo> findByPage(int pageNum, int pageSize, String elderName, Integer status,
                                                LocalDate startDate, LocalDate endDate) {
        Page<MealOrderVo> page = new Page<>(pageNum, pageSize);
        IPage<MealOrderVo> byPage = mealOrderMapper.findByPage(page, elderName, status, startDate, endDate);
        return PageResponse.of(byPage, MealOrderVo.class);
    }

    /**
     * 查询下单套餐内的菜品编号集合(未选择套餐时返回空集合)
     */
    private Set<Long> findSetDishesIds(Long cateringSetId) {
        if (ObjectUtil.isEmpty(cateringSetId)) {
            return Collections.emptySet();
        }
        if (ObjectUtil.isEmpty(cateringSetMapper.findById(cateringSetId))) {
            throw new BaseException("套餐不存在");
        }
        return setDishesMapper.findBySetId(cateringSetId).stream()
                .map(SetDishes::getDishesId)
                .collect(Collectors.toSet());
    }
}
