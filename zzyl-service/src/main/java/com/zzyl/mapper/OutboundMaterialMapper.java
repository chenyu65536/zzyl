package com.zzyl.mapper;

import com.zzyl.entity.OutboundMaterial;
import com.zzyl.vo.OutboundMaterialVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutboundMaterialMapper {

    int insert(OutboundMaterial outboundMaterial);

    List<OutboundMaterial> findByOutboundRecordId(Long outboundRecordId);

    List<OutboundMaterialVo> findVoByOutboundRecordId(Long outboundRecordId);
}
