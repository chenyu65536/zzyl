package com.zzyl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.hutool.core.bean.BeanUtil;
import com.zzyl.base.PageResponse;
import com.zzyl.dto.AlertRuleDto;
import com.zzyl.entity.AlertRule;
import com.zzyl.mapper.AlertRuleMapper;
import com.zzyl.service.AlertRuleService;
import com.zzyl.vo.AlertRuleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AlertRuleServiceImpl implements AlertRuleService {

    @Resource
    private AlertRuleMapper alertRuleMapper;

    @Override
    public PageResponse<AlertRuleVo> getAlertRulePage(Integer pageNum, Integer pageSize, String alertRuleName, String productId, String functionName) {
        Page mpPage = new Page<>(pageNum, pageSize);
        IPage<AlertRuleVo> pageResult = alertRuleMapper.page(mpPage, alertRuleName, productId, functionName);
        pageResult.getRecords().forEach(v -> {
            v.setProductKey(v.getProductId());
            v.setDeviceId(v.getRelatedDevice());
            v.setRules(new StringBuilder("ThingModelPropertyDeviceValue ").append(v.getOperator()).append(v.getValue()).append("持续触发").append(v.getDuration()).append("个周期时发生报警").toString());
        });
        return PageResponse.of(pageResult, AlertRuleVo.class);
    }

    @Override
    public void deleteAlertRule(Long id) {
        alertRuleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateAlertRule(Long id, AlertRuleDto alertRuleDto) {
        AlertRule alertRule = BeanUtil.toBean(alertRuleDto, AlertRule.class);
        alertRule.setProductId(alertRuleDto.getProductKey());
        alertRule.setRelatedDevice(alertRuleDto.getDeviceId());
        alertRuleMapper.updateByPrimaryKeySelective(alertRule);
    }

    @Override
    public AlertRuleVo readAlertRule(Long id) {
        AlertRule alertRule = alertRuleMapper.selectByPrimaryKey(id);
        AlertRuleVo alertRuleVo = BeanUtil.toBean(alertRule, AlertRuleVo.class);

        alertRuleVo.setProductKey(alertRule.getProductId());
        alertRuleVo.setDeviceId(alertRule.getRelatedDevice());
        return alertRuleVo;
    }

    @Override
    public void createAlertRule(AlertRuleDto alertRuleDto) {
        AlertRule alertRule = BeanUtil.toBean(alertRuleDto, AlertRule.class);
        alertRule.setProductId(alertRuleDto.getProductKey());
        alertRule.setRelatedDevice(alertRuleDto.getDeviceId());
        alertRuleMapper.insert(alertRule);
    }

    @Override
    public void enableOrDisable(Long id, Integer status) {
        alertRuleMapper.updateStatus(id, status);
    }
}
