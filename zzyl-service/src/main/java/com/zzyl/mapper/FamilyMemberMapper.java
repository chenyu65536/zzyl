package com.zzyl.mapper;

import com.zzyl.entity.FamilyMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 老人家属Mapper接口
 */
@Mapper
public interface FamilyMemberMapper {

    int insert(FamilyMember familyMember);

    int update(FamilyMember familyMember);

    int deleteById(Long id);

    FamilyMember findById(Long id);

    List<FamilyMember> findByElderId(Long elderId);
}
