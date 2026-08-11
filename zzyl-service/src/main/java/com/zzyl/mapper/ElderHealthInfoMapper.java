package com.zzyl.mapper;

import com.zzyl.entity.ElderHealthInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 老人健康信息Mapper接口
 */
@Mapper
public interface ElderHealthInfoMapper {

    int insert(ElderHealthInfo elderHealthInfo);

    int update(ElderHealthInfo elderHealthInfo);

    ElderHealthInfo findByElderId(Long elderId);
}
