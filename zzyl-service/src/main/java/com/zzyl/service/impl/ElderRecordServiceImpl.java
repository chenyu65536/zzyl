package com.zzyl.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zzyl.dto.ElderHealthDataDto;
import com.zzyl.dto.ElderHealthInfoDto;
import com.zzyl.dto.ElderLifeInfoDto;
import com.zzyl.dto.EmergencyContactDto;
import com.zzyl.dto.FamilyMemberDto;
import com.zzyl.entity.Elder;
import com.zzyl.entity.ElderHealthData;
import com.zzyl.entity.ElderHealthInfo;
import com.zzyl.entity.ElderLifeInfo;
import com.zzyl.entity.ElderRecordLog;
import com.zzyl.entity.EmergencyContact;
import com.zzyl.entity.FamilyMember;
import com.zzyl.exception.BaseException;
import com.zzyl.mapper.ElderHealthDataMapper;
import com.zzyl.mapper.ElderHealthInfoMapper;
import com.zzyl.mapper.ElderLifeInfoMapper;
import com.zzyl.mapper.ElderMapper;
import com.zzyl.mapper.ElderRecordLogMapper;
import com.zzyl.mapper.EmergencyContactMapper;
import com.zzyl.mapper.FamilyMemberMapper;
import com.zzyl.service.ElderRecordService;
import com.zzyl.utils.ObjectUtil;
import com.zzyl.utils.UserThreadLocal;
import com.zzyl.vo.ElderHealthDataVo;
import com.zzyl.vo.ElderHealthInfoVo;
import com.zzyl.vo.ElderLifeInfoVo;
import com.zzyl.vo.ElderRecordLogVo;
import com.zzyl.vo.ElderRecordVo;
import com.zzyl.vo.EmergencyContactVo;
import com.zzyl.vo.FamilyMemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 老人档案管理服务实现
 */
@Slf4j
@Service
@Transactional
public class ElderRecordServiceImpl implements ElderRecordService {

    /**
     * 老人状态-启用
     */
    private static final Integer ELDER_STATUS_ENABLE = 1;

    /**
     * 老人状态-作废(退住中)
     */
    private static final Integer ELDER_STATUS_INVALID = 3;

    @Autowired
    private ElderMapper elderMapper;

    @Autowired
    private ElderHealthInfoMapper elderHealthInfoMapper;

    @Autowired
    private ElderLifeInfoMapper elderLifeInfoMapper;

    @Autowired
    private ElderHealthDataMapper elderHealthDataMapper;

    @Autowired
    private EmergencyContactMapper emergencyContactMapper;

    @Autowired
    private FamilyMemberMapper familyMemberMapper;

    @Autowired
    private ElderRecordLogMapper elderRecordLogMapper;

    /**
     * 查询老人档案详情(聚合)
     */
    @Override
    public ElderRecordVo getRecordDetail(Long elderId) {
        Elder elder = checkElder(elderId);

        ElderRecordVo recordVo = new ElderRecordVo();
        recordVo.setElderId(elder.getId());
        recordVo.setName(elder.getName());
        recordVo.setImage(elder.getImage());
        recordVo.setIdCardNo(elder.getIdCardNo());
        recordVo.setPhone(elder.getPhone());
        recordVo.setAge(elder.getAge());
        recordVo.setSex(elder.getSex());
        recordVo.setBedNumber(elder.getBedNumber());
        recordVo.setStatus(elder.getStatus());

        // 健康信息
        ElderHealthInfo healthInfo = elderHealthInfoMapper.findByElderId(elderId);
        if (ObjectUtil.isNotEmpty(healthInfo)) {
            recordVo.setHealthInfo(BeanUtil.toBean(healthInfo, ElderHealthInfoVo.class));
        }

        // 生活档案
        ElderLifeInfo lifeInfo = elderLifeInfoMapper.findByElderId(elderId);
        if (ObjectUtil.isNotEmpty(lifeInfo)) {
            recordVo.setLifeInfo(BeanUtil.toBean(lifeInfo, ElderLifeInfoVo.class));
        }

        // 最新体检记录
        ElderHealthData latestHealthData = elderHealthDataMapper.findLatestByElderId(elderId);
        if (ObjectUtil.isNotEmpty(latestHealthData)) {
            recordVo.setLatestHealthData(BeanUtil.toBean(latestHealthData, ElderHealthDataVo.class));
        }

        // 体检记录列表
        recordVo.setHealthDataList(elderHealthDataMapper.findByElderId(elderId).stream()
                .map(healthData -> BeanUtil.toBean(healthData, ElderHealthDataVo.class))
                .collect(Collectors.toList()));

        // 紧急联系人列表
        recordVo.setEmergencyContacts(emergencyContactMapper.findByElderId(elderId).stream()
                .map(contact -> BeanUtil.toBean(contact, EmergencyContactVo.class))
                .collect(Collectors.toList()));

        // 家属列表
        recordVo.setFamilyMembers(familyMemberMapper.findByElderId(elderId).stream()
                .map(familyMember -> BeanUtil.toBean(familyMember, FamilyMemberVo.class))
                .collect(Collectors.toList()));

        // 变更记录列表
        recordVo.setChangeLogs(elderRecordLogMapper.findByElderId(elderId).stream()
                .map(recordLog -> BeanUtil.toBean(recordLog, ElderRecordLogVo.class))
                .collect(Collectors.toList()));

        return recordVo;
    }

    /**
     * 保存健康信息(存在则更新,不存在则新增)
     */
    @Override
    public void saveHealthInfo(ElderHealthInfoDto dto) {
        Elder elder = checkElder(dto.getElderId());
        ElderHealthInfo exist = elderHealthInfoMapper.findByElderId(dto.getElderId());
        ElderHealthInfo healthInfo = BeanUtil.toBean(dto, ElderHealthInfo.class);
        if (ObjectUtil.isEmpty(exist)) {
            healthInfo.setCreateBy(UserThreadLocal.getMgtUserId());
            healthInfo.setCreateTime(LocalDateTime.now());
            elderHealthInfoMapper.insert(healthInfo);
        } else {
            healthInfo.setId(exist.getId());
            healthInfo.setUpdateBy(UserThreadLocal.getMgtUserId());
            healthInfo.setUpdateTime(LocalDateTime.now());
            elderHealthInfoMapper.update(healthInfo);
        }
        recordLog(dto.getElderId(), "健康档案修改", "保存老人[" + elder.getName() + "]健康信息");
    }

    /**
     * 保存生活档案(存在则更新,不存在则新增)
     */
    @Override
    public void saveLifeInfo(ElderLifeInfoDto dto) {
        Elder elder = checkElder(dto.getElderId());
        ElderLifeInfo exist = elderLifeInfoMapper.findByElderId(dto.getElderId());
        ElderLifeInfo lifeInfo = BeanUtil.toBean(dto, ElderLifeInfo.class);
        if (ObjectUtil.isEmpty(exist)) {
            lifeInfo.setCreateBy(UserThreadLocal.getMgtUserId());
            lifeInfo.setCreateTime(LocalDateTime.now());
            elderLifeInfoMapper.insert(lifeInfo);
        } else {
            lifeInfo.setId(exist.getId());
            lifeInfo.setUpdateBy(UserThreadLocal.getMgtUserId());
            lifeInfo.setUpdateTime(LocalDateTime.now());
            elderLifeInfoMapper.update(lifeInfo);
        }
        recordLog(dto.getElderId(), "生活档案修改", "保存老人[" + elder.getName() + "]生活档案");
    }

    /**
     * 新增体检记录
     */
    @Override
    public void addHealthData(ElderHealthDataDto dto) {
        checkElder(dto.getElderId());
        ElderHealthData healthData = BeanUtil.toBean(dto, ElderHealthData.class);
        healthData.setCreateBy(UserThreadLocal.getMgtUserId());
        healthData.setCreateTime(LocalDateTime.now());
        elderHealthDataMapper.insert(healthData);
        recordLog(dto.getElderId(), "健康档案修改", "新增体检记录");
    }

    /**
     * 更新体检记录
     */
    @Override
    public void updateHealthData(Long id, ElderHealthDataDto dto) {
        ElderHealthData exist = elderHealthDataMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("体检记录不存在");
        }
        ElderHealthData healthData = BeanUtil.toBean(dto, ElderHealthData.class);
        healthData.setId(id);
        healthData.setUpdateBy(UserThreadLocal.getMgtUserId());
        healthData.setUpdateTime(LocalDateTime.now());
        elderHealthDataMapper.update(healthData);
        recordLog(exist.getElderId(), "健康档案修改", "修改体检记录[" + id + "]");
    }

    /**
     * 删除体检记录
     */
    @Override
    public void deleteHealthData(Long id) {
        ElderHealthData exist = elderHealthDataMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("体检记录不存在");
        }
        elderHealthDataMapper.deleteById(id);
        recordLog(exist.getElderId(), "健康档案修改", "删除体检记录[" + id + "]");
    }

    /**
     * 新增紧急联系人
     */
    @Override
    public void addEmergencyContact(EmergencyContactDto dto) {
        checkElder(dto.getElderId());
        EmergencyContact contact = BeanUtil.toBean(dto, EmergencyContact.class);
        contact.setCreateBy(UserThreadLocal.getMgtUserId());
        contact.setCreateTime(LocalDateTime.now());
        emergencyContactMapper.insert(contact);
        recordLog(dto.getElderId(), "联系人变更", "新增紧急联系人[" + dto.getName() + "]");
    }

    /**
     * 更新紧急联系人
     */
    @Override
    public void updateEmergencyContact(Long id, EmergencyContactDto dto) {
        EmergencyContact exist = emergencyContactMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("紧急联系人不存在");
        }
        EmergencyContact contact = BeanUtil.toBean(dto, EmergencyContact.class);
        contact.setId(id);
        contact.setUpdateBy(UserThreadLocal.getMgtUserId());
        contact.setUpdateTime(LocalDateTime.now());
        emergencyContactMapper.update(contact);
        recordLog(exist.getElderId(), "联系人变更", "修改紧急联系人[" + exist.getName() + "]");
    }

    /**
     * 删除紧急联系人
     */
    @Override
    public void deleteEmergencyContact(Long id) {
        EmergencyContact exist = emergencyContactMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("紧急联系人不存在");
        }
        emergencyContactMapper.deleteById(id);
        recordLog(exist.getElderId(), "联系人变更", "删除紧急联系人[" + exist.getName() + "]");
    }

    /**
     * 新增家属
     */
    @Override
    public void addFamilyMember(FamilyMemberDto dto) {
        checkElder(dto.getElderId());
        FamilyMember familyMember = BeanUtil.toBean(dto, FamilyMember.class);
        familyMember.setCreateBy(UserThreadLocal.getMgtUserId());
        familyMember.setCreateTime(LocalDateTime.now());
        familyMemberMapper.insert(familyMember);
        recordLog(dto.getElderId(), "家属变更", "新增家属[" + dto.getName() + "]");
    }

    /**
     * 更新家属
     */
    @Override
    public void updateFamilyMember(Long id, FamilyMemberDto dto) {
        FamilyMember exist = familyMemberMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("家属不存在");
        }
        FamilyMember familyMember = BeanUtil.toBean(dto, FamilyMember.class);
        familyMember.setId(id);
        familyMember.setUpdateBy(UserThreadLocal.getMgtUserId());
        familyMember.setUpdateTime(LocalDateTime.now());
        familyMemberMapper.update(familyMember);
        recordLog(exist.getElderId(), "家属变更", "修改家属[" + exist.getName() + "]");
    }

    /**
     * 删除家属(逻辑删除)
     */
    @Override
    public void deleteFamilyMember(Long id) {
        FamilyMember exist = familyMemberMapper.findById(id);
        if (ObjectUtil.isEmpty(exist)) {
            throw new BaseException("家属不存在");
        }
        FamilyMember familyMember = new FamilyMember();
        familyMember.setId(id);
        familyMember.setDelFlag("1");
        familyMember.setUpdateBy(UserThreadLocal.getMgtUserId());
        familyMember.setUpdateTime(LocalDateTime.now());
        familyMemberMapper.update(familyMember);
        recordLog(exist.getElderId(), "家属变更", "删除家属[" + exist.getName() + "]");
    }

    /**
     * 档案作废
     */
    @Override
    public void invalidRecord(Long elderId) {
        Elder elder = checkElder(elderId);
        Elder update = new Elder();
        update.setId(elderId);
        update.setStatus(ELDER_STATUS_INVALID);
        update.setUpdateBy(UserThreadLocal.getMgtUserId());
        update.setUpdateTime(LocalDateTime.now());
        elderMapper.updateByPrimaryKeySelective(update);
        recordLog(elderId, "档案作废", "老人[" + elder.getName() + "]档案作废");
    }

    /**
     * 档案恢复
     */
    @Override
    public void restoreRecord(Long elderId) {
        Elder elder = checkElder(elderId);
        Elder update = new Elder();
        update.setId(elderId);
        update.setStatus(ELDER_STATUS_ENABLE);
        update.setUpdateBy(UserThreadLocal.getMgtUserId());
        update.setUpdateTime(LocalDateTime.now());
        elderMapper.updateByPrimaryKeySelective(update);
        recordLog(elderId, "档案恢复", "老人[" + elder.getName() + "]档案恢复");
    }

    /**
     * 查询档案变更记录
     */
    @Override
    public List<ElderRecordLogVo> getChangeLogs(Long elderId) {
        return elderRecordLogMapper.findByElderId(elderId).stream()
                .map(recordLog -> BeanUtil.toBean(recordLog, ElderRecordLogVo.class))
                .collect(Collectors.toList());
    }

    /**
     * 校验老人是否存在
     */
    private Elder checkElder(Long elderId) {
        Elder elder = elderMapper.selectByPrimaryKey(elderId);
        if (ObjectUtil.isEmpty(elder)) {
            throw new BaseException("老人不存在");
        }
        return elder;
    }

    /**
     * 写入档案变更记录
     */
    private void recordLog(Long elderId, String changeType, String changeContent) {
        ElderRecordLog recordLog = new ElderRecordLog();
        recordLog.setElderId(elderId);
        recordLog.setChangeType(changeType);
        recordLog.setChangeContent(changeContent);
        recordLog.setCreateBy(UserThreadLocal.getMgtUserId());
        recordLog.setCreateTime(LocalDateTime.now());
        elderRecordLogMapper.insert(recordLog);
    }
}
