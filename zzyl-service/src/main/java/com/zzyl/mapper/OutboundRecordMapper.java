package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.OutboundRecord;
import com.zzyl.vo.OutboundRecordVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface OutboundRecordMapper {

    int insert(OutboundRecord outboundRecord);

    int update(OutboundRecord outboundRecord);

    OutboundRecord findById(Long id);

    OutboundRecordVo findVoById(Long id);

    IPage<OutboundRecordVo> findByPage(Page<OutboundRecordVo> page,
                                       @Param("warehouseId") Long warehouseId,
                                       @Param("materialUse") String materialUse,
                                       @Param("status") Integer status,
                                       @Param("startTime") LocalDateTime startTime,
                                       @Param("endTime") LocalDateTime endTime);
}
