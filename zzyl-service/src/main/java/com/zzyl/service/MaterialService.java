package com.zzyl.service;

import com.zzyl.base.PageResponse;
import com.zzyl.dto.MaterialDto;
import com.zzyl.dto.MaterialTypeDto;
import com.zzyl.vo.MaterialTypeVo;
import com.zzyl.vo.MaterialVo;

import java.util.List;

/**
 * 物资管理服务(含物资类别)
 */
public interface MaterialService {

    // ==================== 物资类别 ====================

    void addType(MaterialTypeDto dto);

    void updateType(Long id, MaterialTypeDto dto);

    void deleteTypeById(Long id);

    MaterialTypeVo findTypeById(Long id);

    List<MaterialTypeVo> findAllTypes(String name);

    // ==================== 物资 ====================

    void add(MaterialDto dto);

    void update(Long id, MaterialDto dto);

    void deleteById(Long id);

    MaterialVo findById(Long id);

    List<MaterialVo> findAll(String name);

    PageResponse<MaterialVo> findByPage(int pageNum, int pageSize, String name, Long typeId);
}
