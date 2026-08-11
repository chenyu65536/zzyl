package com.zzyl.service;

import com.zzyl.base.PageResponse;
import com.zzyl.dto.DishesDto;
import com.zzyl.dto.DishesTypeDto;
import com.zzyl.vo.DishesTypeVo;
import com.zzyl.vo.DishesVo;

import java.util.List;

/**
 * 菜品管理服务(含菜品类别)
 */
public interface DishesService {

    // ==================== 菜品类别 ====================

    void addType(DishesTypeDto dto);

    void updateType(Long id, DishesTypeDto dto);

    void deleteTypeById(Long id);

    DishesTypeVo findTypeById(Long id);

    List<DishesTypeVo> findAllTypes(String name);

    // ==================== 菜品 ====================

    void add(DishesDto dto);

    void update(Long id, DishesDto dto);

    void deleteById(Long id);

    DishesVo findById(Long id);

    PageResponse<DishesVo> findByPage(int pageNum, int pageSize, String name, Long typeId);
}
