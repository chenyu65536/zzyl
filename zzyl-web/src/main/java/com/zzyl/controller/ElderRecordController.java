package com.zzyl.controller;

import com.zzyl.base.ResponseResult;
import com.zzyl.dto.ElderHealthDataDto;
import com.zzyl.dto.ElderHealthInfoDto;
import com.zzyl.dto.ElderLifeInfoDto;
import com.zzyl.dto.EmergencyContactDto;
import com.zzyl.dto.FamilyMemberDto;
import com.zzyl.service.ElderRecordService;
import com.zzyl.vo.ElderRecordLogVo;
import com.zzyl.vo.ElderRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 老人档案管理
 */
@RestController
@RequestMapping("/elder-record")
@Api(tags = "老人档案管理")
public class ElderRecordController extends BaseController {

    @Autowired
    private ElderRecordService elderRecordService;

    @GetMapping("/{elderId}")
    @ApiOperation("查询老人档案详情")
    public ResponseResult<ElderRecordVo> getRecordDetail(@PathVariable Long elderId) {
        return success(elderRecordService.getRecordDetail(elderId));
    }

    // ==================== 健康信息/生活档案 ====================

    @PostMapping("/health-info")
    @ApiOperation("保存健康信息")
    public ResponseResult saveHealthInfo(@Validated @RequestBody ElderHealthInfoDto dto) {
        elderRecordService.saveHealthInfo(dto);
        return success();
    }

    @PostMapping("/life-info")
    @ApiOperation("保存生活档案")
    public ResponseResult saveLifeInfo(@Validated @RequestBody ElderLifeInfoDto dto) {
        elderRecordService.saveLifeInfo(dto);
        return success();
    }

    // ==================== 体检记录 ====================

    @PostMapping("/health-data")
    @ApiOperation("新增体检记录")
    public ResponseResult addHealthData(@Validated @RequestBody ElderHealthDataDto dto) {
        elderRecordService.addHealthData(dto);
        return success();
    }

    @PutMapping("/health-data/{id}")
    @ApiOperation("更新体检记录")
    public ResponseResult updateHealthData(@PathVariable Long id, @Validated @RequestBody ElderHealthDataDto dto) {
        elderRecordService.updateHealthData(id, dto);
        return success();
    }

    @DeleteMapping("/health-data/{id}")
    @ApiOperation("删除体检记录")
    public ResponseResult deleteHealthData(@PathVariable Long id) {
        elderRecordService.deleteHealthData(id);
        return success();
    }

    // ==================== 紧急联系人 ====================

    @PostMapping("/emergency-contact")
    @ApiOperation("新增紧急联系人")
    public ResponseResult addEmergencyContact(@Validated @RequestBody EmergencyContactDto dto) {
        elderRecordService.addEmergencyContact(dto);
        return success();
    }

    @PutMapping("/emergency-contact/{id}")
    @ApiOperation("更新紧急联系人")
    public ResponseResult updateEmergencyContact(@PathVariable Long id, @Validated @RequestBody EmergencyContactDto dto) {
        elderRecordService.updateEmergencyContact(id, dto);
        return success();
    }

    @DeleteMapping("/emergency-contact/{id}")
    @ApiOperation("删除紧急联系人")
    public ResponseResult deleteEmergencyContact(@PathVariable Long id) {
        elderRecordService.deleteEmergencyContact(id);
        return success();
    }

    // ==================== 家属 ====================

    @PostMapping("/family-member")
    @ApiOperation("新增家属")
    public ResponseResult addFamilyMember(@Validated @RequestBody FamilyMemberDto dto) {
        elderRecordService.addFamilyMember(dto);
        return success();
    }

    @PutMapping("/family-member/{id}")
    @ApiOperation("更新家属")
    public ResponseResult updateFamilyMember(@PathVariable Long id, @Validated @RequestBody FamilyMemberDto dto) {
        elderRecordService.updateFamilyMember(id, dto);
        return success();
    }

    @DeleteMapping("/family-member/{id}")
    @ApiOperation("删除家属")
    public ResponseResult deleteFamilyMember(@PathVariable Long id) {
        elderRecordService.deleteFamilyMember(id);
        return success();
    }

    // ==================== 档案作废/恢复 ====================

    @PutMapping("/{elderId}/invalid")
    @ApiOperation("档案作废")
    public ResponseResult invalidRecord(@PathVariable Long elderId) {
        elderRecordService.invalidRecord(elderId);
        return success();
    }

    @PutMapping("/{elderId}/restore")
    @ApiOperation("档案恢复")
    public ResponseResult restoreRecord(@PathVariable Long elderId) {
        elderRecordService.restoreRecord(elderId);
        return success();
    }

    // ==================== 变更记录 ====================

    @GetMapping("/{elderId}/logs")
    @ApiOperation("查询档案变更记录")
    public ResponseResult<List<ElderRecordLogVo>> getChangeLogs(@PathVariable Long elderId) {
        return success(elderRecordService.getChangeLogs(elderId));
    }
}
