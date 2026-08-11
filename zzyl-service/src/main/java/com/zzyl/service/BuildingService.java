package com.zzyl.service;

import com.zzyl.base.PageResponse;
import com.zzyl.dto.BuildingDto;
import com.zzyl.vo.BuildingVo;
import com.zzyl.vo.FloorVo;

import java.util.List;

/**
 * 楼栋管理服务
 */
public interface BuildingService {

    void add(BuildingDto dto);

    void update(Long id, BuildingDto dto);

    void deleteById(Long id);

    BuildingVo findById(Long id);

    List<BuildingVo> findAll();

    PageResponse<BuildingVo> findByPage(int pageNum, int pageSize, String name);

    List<FloorVo> findFloorsByBuildingId(Long buildingId);
}
