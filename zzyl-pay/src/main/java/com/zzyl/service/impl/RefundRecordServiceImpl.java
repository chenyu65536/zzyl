package com.zzyl.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzyl.base.PageResponse;
import com.zzyl.entity.RefundRecord;
import com.zzyl.enums.RefundStatusEnum;
import com.zzyl.mapper.RefundRecordMapper;
import com.zzyl.service.RefundRecordService;
import com.zzyl.vo.RefundRecordVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  退款记录服务实现类
 */
@Service
public class RefundRecordServiceImpl implements RefundRecordService {

    @Resource
    private RefundRecordMapper refundRecordMapper;

    /**
     * 根据退款编号查询退款记录
     * @param refundNo 退款编号
     * @return 退款记录
     */
    @Override
    public RefundRecordVo findByRefundNo(Long refundNo) {
        return refundRecordMapper.selectByRefundNo(refundNo);
    }

    /**
     * 根据交易订单编号查询退款记录列表
     * @param tradingOrderNo 交易订单编号
     * @return 退款记录列表
     */
    @Override
    public List<RefundRecord> findListByTradingOrderNo(Long tradingOrderNo) {
        return refundRecordMapper.selectListByTradingOrderNo(tradingOrderNo);
    }

    /**
     * 根据商品订单编号查询退款记录列表
     * @param productOrderNo 商品订单编号
     * @return 退款记录列表
     */
    @Override
    public List<RefundRecord> findListByProductOrderNo(Long productOrderNo) {
        return refundRecordMapper.selectListByProductOrderNo(productOrderNo);
    }

    /**
     * 根据退款状态查询退款记录列表
     * @param refundStatus 退款状态
     * @param count 查询数量
     * @return 退款记录列表
     */
    @Override
    public List<RefundRecord> findListByRefundStatus(RefundStatusEnum refundStatus, Integer count) {
        count = NumberUtil.max(count, 10);
        return refundRecordMapper.selectListByRefundStatus(refundStatus.getCode(), count);
    }

    /**
     * 保存或更新退款记录
     * @param refundRecord 退款记录
     */
    @Override
    public void saveOrUpdate(RefundRecord refundRecord) {
        if (refundRecord.getId() == null) {
            refundRecordMapper.insert(refundRecord);
        } else {
            refundRecordMapper.updateByPrimaryKey(refundRecord);
        }
    }

    /**
     * 根据商品订单编号查询"退款中"状态的退款记录
     * <p>
     * 修改点：修复退款幂等校验失效问题。原实现恒返回 null，
     * 导致 {@code BasicBeforePayHandler#idempotentRefundTrading} 中
     * "存在退款中记录则拒绝再次退款" 的防重复退款校验形同虚设，
     * 同一订单可被并发/重复发起退款。
     * 现改为：复用已有 Mapper 方法查询该订单全部退款记录，
     * 过滤出状态为 SENDING（退款中）的第一条返回；无则返回 null。
     *
     * @param productOrderNo 商品订单编号
     * @return 退款中的退款记录，不存在则返回 null
     */
    @Override
    public RefundRecord findRefundRecordByProductOrderNoAndSending(Long productOrderNo) {
        if (productOrderNo == null) {
            return null;
        }
        List<RefundRecord> refundRecords = refundRecordMapper.selectListByProductOrderNo(productOrderNo);
        if (CollUtil.isEmpty(refundRecords)) {
            return null;
        }
        return refundRecords.stream()
                .filter(record -> RefundStatusEnum.SENDING.getCode().equals(record.getRefundStatus()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public PageResponse<RefundRecordVo> queryRefundRecord(RefundRecordVo refundRecordVo) {
        try {
            if (ObjectUtil.isNotEmpty(refundRecordVo.getRefundNoStr())) {
                Long aLong = Long.valueOf(refundRecordVo.getRefundNoStr());
                refundRecordVo.setRefundNo(aLong);
            }
        } catch (Exception e) {
            return PageResponse.of(new Page<>(),RefundRecordVo.class);
        }
        PageHelper.startPage(refundRecordVo.getPageNum(), refundRecordVo.getPageSize());
        Page<List<RefundRecordVo>> lists = refundRecordMapper.queryRefundRecord(refundRecordVo);
        return PageResponse.of(lists,RefundRecordVo.class);
    }
}


