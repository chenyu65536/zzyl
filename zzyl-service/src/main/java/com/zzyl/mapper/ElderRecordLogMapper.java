package com.zzyl.mapper;

import com.zzyl.entity.ElderRecordLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 老人档案变更记录Mapper接口
 */
@Mapper
public interface ElderRecordLogMapper {

    int insert(ElderRecordLog elderRecordLog);

    List<ElderRecordLog> findByElderId(Long elderId);
}
