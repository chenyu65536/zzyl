package com.zzyl.service;

import com.zzyl.base.PageResponse;
import com.zzyl.dto.WarehouseDto;
import com.zzyl.vo.WarehouseVo;

import java.util.List;

/**
 * 仓库管理服务
 */
public interface WarehouseService {

    void add(WarehouseDto dto);

    void update(Long id, WarehouseDto dto);

    void deleteById(Long id);

    WarehouseVo findById(Long id);

    List<WarehouseVo> findAll();

    PageResponse<WarehouseVo> findByPage(int pageNum, int pageSize, String name);
}
