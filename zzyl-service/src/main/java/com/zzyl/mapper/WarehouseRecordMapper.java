package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.WarehouseRecord;
import com.zzyl.vo.WarehouseRecordVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface WarehouseRecordMapper {

    int insert(WarehouseRecord warehouseRecord);

    int update(WarehouseRecord warehouseRecord);

    WarehouseRecord findById(Long id);

    WarehouseRecordVo findVoById(Long id);

    IPage<WarehouseRecordVo> findByPage(Page<WarehouseRecordVo> page,
                                        @Param("warehouseId") Long warehouseId,
                                        @Param("source") String source,
                                        @Param("status") Integer status,
                                        @Param("startTime") LocalDateTime startTime,
                                        @Param("endTime") LocalDateTime endTime);
}
