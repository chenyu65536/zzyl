package com.zzyl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.base.PageResponse;
import com.zzyl.dto.BuildingDto;
import com.zzyl.entity.Building;
import com.zzyl.entity.Floor;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.BuildingMapper;
import com.zzyl.mapper.FloorMapper;
import com.zzyl.service.BuildingService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.BuildingVo;
import com.zzyl.vo.FloorVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 楼栋管理服务实现
 */
@Slf4j
@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingMapper buildingMapper;

    @Autowired
    private FloorMapper floorMapper;

    /**
     * 新增楼栋
     */
    @Override
    public void add(BuildingDto dto) {
        // 校验楼栋名称是否已存在
        if (ObjectUtil.isNotEmpty(buildingMapper.findByName(dto.getName()))) {
            throw new BaseException("楼栋名称已存在");
        }
        Building building = BeanUtil.toBean(dto, Building.class);
        building.setCreateBy(UserThreadLocal.getMgtUserId());
        building.setCreateTime(LocalDateTime.now());
        buildingMapper.insert(building);
    }

    /**
     * 更新楼栋
     */
    @Override
    public void update(Long id, BuildingDto dto) {
        Building exist = buildingMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("楼栋不存在");
        }
        // 校验楼栋名称是否与其他楼栋重复
        Building byName = buildingMapper.findByName(dto.getName());
        if (ObjectUtil.isNotEmpty(byName) && !Objects.equals(byName.getId(), id)) {
            throw new BaseException("楼栋名称已存在");
        }
        Building building = BeanUtil.toBean(dto, Building.class);
        building.setId(id);
        building.setUpdateBy(UserThreadLocal.getMgtUserId());
        building.setUpdateTime(LocalDateTime.now());
        buildingMapper.update(building);
    }

    /**
     * 删除楼栋(物理删除,楼栋下存在楼层时禁止删除)
     */
    @Override
    public void deleteById(Long id) {
        Building exist = buildingMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("楼栋不存在");
        }
        List<Floor> floors = floorMapper.findByBuildingId(id);
        if (ObjectUtil.isNotEmpty(floors)) {
            throw new BaseException("楼栋下存在楼层,不能删除");
        }
        buildingMapper.deleteById(id);
    }

    /**
     * 根据ID查询楼栋
     */
    @Override
    public BuildingVo findById(Long id) {
        Building building = buildingMapper.findById(id);
        if (ObjectUtil.isEmpty(building)) {
            return null;
        }
        return BeanUtil.toBean(building, BuildingVo.class);
    }

    /**
     * 查询所有楼栋(下拉)
     */
    @Override
    public List<BuildingVo> findAll() {
        return buildingMapper.findAll().stream()
                .map(building -> BeanUtil.toBean(building, BuildingVo.class))
                .collect(Collectors.toList());
    }

    /**
     * 分页查询楼栋
     */
    @Override
    public PageResponse<BuildingVo> findByPage(int pageNum, int pageSize, String name) {
        Page<BuildingVo> page = new Page<>(pageNum, pageSize);
        IPage<BuildingVo> byPage = buildingMapper.findByPage(page, name);
        return PageResponse.of(byPage, BuildingVo.class);
    }

    /**
     * 查询楼栋下的楼层列表
     */
    @Override
    public List<FloorVo> findFloorsByBuildingId(Long buildingId) {
        return floorMapper.findByBuildingId(buildingId).stream()
                .map(floor -> BeanUtil.toBean(floor, FloorVo.class))
                .collect(Collectors.toList());
    }
}
