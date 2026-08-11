package com.zzyl.service;

import com.zzyl.base.PageResponse;
import com.zzyl.dto.CateringSetDto;
import com.zzyl.vo.CateringSetVo;

/**
 * 餐饮套餐服务
 */
public interface CateringSetService {

    /**
     * 新增套餐(含套餐菜品关联)
     */
    void add(CateringSetDto dto);

    /**
     * 更新套餐(重建套餐菜品关联)
     */
    void update(Long id, CateringSetDto dto);

    /**
     * 删除套餐(逻辑删除)
     */
    void deleteById(Long id);

    /**
     * 根据ID查询套餐详情(含套餐内菜品列表)
     */
    CateringSetVo findById(Long id);

    /**
     * 分页查询套餐
     */
    PageResponse<CateringSetVo> findByPage(int pageNum, int pageSize, String name);
}
