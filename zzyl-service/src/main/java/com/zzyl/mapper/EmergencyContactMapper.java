package com.zzyl.mapper;

import com.zzyl.entity.EmergencyContact;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 紧急联系人Mapper接口
 */
@Mapper
public interface EmergencyContactMapper {

    int insert(EmergencyContact emergencyContact);

    int update(EmergencyContact emergencyContact);

    int deleteById(Long id);

    EmergencyContact findById(Long id);

    List<EmergencyContact> findByElderId(Long elderId);
}
