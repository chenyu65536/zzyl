package com.zzyl.service;

import com.zzyl.dto.ElderHealthDataDto;
import com.zzyl.dto.ElderHealthInfoDto;
import com.zzyl.dto.ElderLifeInfoDto;
import com.zzyl.dto.EmergencyContactDto;
import com.zzyl.dto.FamilyMemberDto;
import com.zzyl.vo.ElderRecordLogVo;
import com.zzyl.vo.ElderRecordVo;

import java.util.List;

/**
 * 老人档案管理服务
 */
public interface ElderRecordService {

    /**
     * 查询老人档案详情(聚合)
     *
     * @param elderId 老人编号
     * @return 档案详情
     */
    ElderRecordVo getRecordDetail(Long elderId);

    /**
     * 保存健康信息(存在则更新,不存在则新增)
     *
     * @param dto 健康信息
     */
    void saveHealthInfo(ElderHealthInfoDto dto);

    /**
     * 保存生活档案(存在则更新,不存在则新增)
     *
     * @param dto 生活档案
     */
    void saveLifeInfo(ElderLifeInfoDto dto);

    /**
     * 新增体检记录
     *
     * @param dto 体检记录
     */
    void addHealthData(ElderHealthDataDto dto);

    /**
     * 更新体检记录
     *
     * @param id  体检记录编号
     * @param dto 体检记录
     */
    void updateHealthData(Long id, ElderHealthDataDto dto);

    /**
     * 删除体检记录
     *
     * @param id 体检记录编号
     */
    void deleteHealthData(Long id);

    /**
     * 新增紧急联系人
     *
     * @param dto 紧急联系人
     */
    void addEmergencyContact(EmergencyContactDto dto);

    /**
     * 更新紧急联系人
     *
     * @param id  紧急联系人编号
     * @param dto 紧急联系人
     */
    void updateEmergencyContact(Long id, EmergencyContactDto dto);

    /**
     * 删除紧急联系人
     *
     * @param id 紧急联系人编号
     */
    void deleteEmergencyContact(Long id);

    /**
     * 新增家属
     *
     * @param dto 家属
     */
    void addFamilyMember(FamilyMemberDto dto);

    /**
     * 更新家属
     *
     * @param id  家属编号
     * @param dto 家属
     */
    void updateFamilyMember(Long id, FamilyMemberDto dto);

    /**
     * 删除家属(逻辑删除)
     *
     * @param id 家属编号
     */
    void deleteFamilyMember(Long id);

    /**
     * 档案作废
     *
     * @param elderId 老人编号
     */
    void invalidRecord(Long elderId);

    /**
     * 档案恢复
     *
     * @param elderId 老人编号
     */
    void restoreRecord(Long elderId);

    /**
     * 查询档案变更记录
     *
     * @param elderId 老人编号
     * @return 变更记录列表
     */
    List<ElderRecordLogVo> getChangeLogs(Long elderId);
}
