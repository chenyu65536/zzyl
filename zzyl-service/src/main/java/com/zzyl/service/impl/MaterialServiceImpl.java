package com.zzyl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.base.PageResponse;
import com.zzyl.dto.MaterialDto;
import com.zzyl.dto.MaterialTypeDto;
import com.zzyl.entity.Material;
import com.zzyl.entity.MaterialType;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.MaterialMapper;
import com.zzyl.mapper.MaterialTypeMapper;
import com.zzyl.service.MaterialService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.MaterialTypeVo;
import com.zzyl.vo.MaterialVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 物资管理服务实现(含物资类别)
 */
@Slf4j
@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {

    /**
     * 物资类别数量上限
     */
    private static final int TYPE_MAX_TOTAL = 50;

    @Autowired
    private MaterialTypeMapper materialTypeMapper;

    @Autowired
    private MaterialMapper materialMapper;

    // ==================== 物资类别 ====================

    /**
     * 新增物资类别
     */
    @Override
    public void addType(MaterialTypeDto dto) {
        if (ObjectUtil.isNotEmpty(materialTypeMapper.findByName(dto.getName()))) {
            throw new BaseException("物资类别名称已存在");
        }
        if (materialTypeMapper.countAll() >= TYPE_MAX_TOTAL) {
            throw new BaseException("物资类别数量已达上限");
        }
        MaterialType materialType = BeanUtil.toBean(dto, MaterialType.class);
        materialType.setCreateBy(UserThreadLocal.getMgtUserId());
        materialType.setCreateTime(LocalDateTime.now());
        materialTypeMapper.insert(materialType);
    }

    /**
     * 更新物资类别
     */
    @Override
    public void updateType(Long id, MaterialTypeDto dto) {
        MaterialType byName = materialTypeMapper.findByName(dto.getName());
        if (ObjectUtil.isNotEmpty(byName) && !Objects.equals(byName.getId(), id)) {
            throw new BaseException("物资类别名称已存在");
        }
        MaterialType materialType = BeanUtil.toBean(dto, MaterialType.class);
        materialType.setId(id);
        materialType.setUpdateBy(UserThreadLocal.getMgtUserId());
        materialType.setUpdateTime(LocalDateTime.now());
        materialTypeMapper.update(materialType);
    }

    /**
     * 删除物资类别(类别下存在物资时禁止删除)
     */
    @Override
    public void deleteTypeById(Long id) {
        List<Material> materials = materialMapper.findByTypeId(id);
        if (ObjectUtil.isNotEmpty(materials)) {
            throw new BaseException("该类别下存在物资,不能删除");
        }
        MaterialType materialType = new MaterialType();
        materialType.setId(id);
        materialType.setDelFlag(1);
        materialType.setUpdateBy(UserThreadLocal.getMgtUserId());
        materialType.setUpdateTime(LocalDateTime.now());
        materialTypeMapper.update(materialType);
    }

    /**
     * 根据ID查询物资类别
     */
    @Override
    public MaterialTypeVo findTypeById(Long id) {
        MaterialType materialType = materialTypeMapper.findById(id);
        if (ObjectUtil.isEmpty(materialType)) {
            return null;
        }
        return BeanUtil.toBean(materialType, MaterialTypeVo.class);
    }

    /**
     * 查询所有物资类别(下拉)
     */
    @Override
    public List<MaterialTypeVo> findAllTypes(String name) {
        return materialTypeMapper.findAll(name).stream()
                .map(materialType -> BeanUtil.toBean(materialType, MaterialTypeVo.class))
                .collect(Collectors.toList());
    }

    // ==================== 物资 ====================

    /**
     * 新增物资
     */
    @Override
    public void add(MaterialDto dto) {
        if (ObjectUtil.isNotEmpty(materialMapper.findByTypeIdAndName(dto.getTypeId(), dto.getName()))) {
            throw new BaseException("该类别下物资名称已存在");
        }
        Material material = BeanUtil.toBean(dto, Material.class);
        material.setCreateBy(UserThreadLocal.getMgtUserId());
        material.setCreateTime(LocalDateTime.now());
        materialMapper.insert(material);
    }

    /**
     * 更新物资
     */
    @Override
    public void update(Long id, MaterialDto dto) {
        Material byName = materialMapper.findByTypeIdAndName(dto.getTypeId(), dto.getName());
        if (ObjectUtil.isNotEmpty(byName) && !Objects.equals(byName.getId(), id)) {
            throw new BaseException("该类别下物资名称已存在");
        }
        Material material = BeanUtil.toBean(dto, Material.class);
        material.setId(id);
        material.setUpdateBy(UserThreadLocal.getMgtUserId());
        material.setUpdateTime(LocalDateTime.now());
        materialMapper.update(material);
    }

    /**
     * 删除物资(逻辑删除)
     */
    @Override
    public void deleteById(Long id) {
        Material material = new Material();
        material.setId(id);
        material.setDelFlag(1);
        material.setUpdateBy(UserThreadLocal.getMgtUserId());
        material.setUpdateTime(LocalDateTime.now());
        materialMapper.update(material);
    }

    /**
     * 根据ID查询物资
     */
    @Override
    public MaterialVo findById(Long id) {
        Material material = materialMapper.findById(id);
        if (ObjectUtil.isEmpty(material)) {
            return null;
        }
        return BeanUtil.toBean(material, MaterialVo.class);
    }

    /**
     * 查询所有物资(下拉)
     */
    @Override
    public List<MaterialVo> findAll(String name) {
        return materialMapper.findAll(name).stream()
                .map(material -> BeanUtil.toBean(material, MaterialVo.class))
                .collect(Collectors.toList());
    }

    /**
     * 分页查询物资
     */
    @Override
    public PageResponse<MaterialVo> findByPage(int pageNum, int pageSize, String name, Long typeId) {
        Page<MaterialVo> page = new Page<>(pageNum, pageSize);
        IPage<MaterialVo> byPage = materialMapper.findByPage(page, name, typeId);
        return PageResponse.of(byPage, MaterialVo.class);
    }
}
