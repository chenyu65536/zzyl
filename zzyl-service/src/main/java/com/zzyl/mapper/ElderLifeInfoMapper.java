package com.zzyl.mapper;

import com.zzyl.entity.ElderLifeInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 老人生活档案Mapper接口
 */
@Mapper
public interface ElderLifeInfoMapper {

    int insert(ElderLifeInfo elderLifeInfo);

    int update(ElderLifeInfo elderLifeInfo);

    ElderLifeInfo findByElderId(Long elderId);
}
