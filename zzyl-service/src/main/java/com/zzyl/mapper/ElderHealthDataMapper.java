package com.zzyl.mapper;

import com.zzyl.entity.ElderHealthData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 老人健康数据(体检记录)Mapper接口
 */
@Mapper
public interface ElderHealthDataMapper {

    int insert(ElderHealthData elderHealthData);

    int update(ElderHealthData elderHealthData);

    int deleteById(Long id);

    ElderHealthData findById(Long id);

    List<ElderHealthData> findByElderId(Long elderId);

    ElderHealthData findLatestByElderId(Long elderId);
}
