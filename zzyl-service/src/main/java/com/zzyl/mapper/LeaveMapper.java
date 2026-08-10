package com.zzyl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzyl.entity.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 请假Mapper
 */
@Mapper
public interface LeaveMapper {

    void createLeave(Leave leave);

    @Select("SELECT * FROM leave WHERE leave_code = #{leaveCode}")
    Leave getLeaveByCode(String leaveCode);

    @Select("SELECT * FROM leave WHERE elder_id = #{elderId} AND status = 1")
    Leave selectByElderId(Long elderId);

    @Select("SELECT * FROM leave WHERE leave_code = #{leaveCode} AND status = 1")
    Leave selectByCode(String leaveCode);

    @Update("UPDATE leave SET flow_status = #{flowStatus} WHERE id = #{id}")
    void updateLeaveFlowStatus(@Param("id") Long id, @Param("flowStatus") Integer flowStatus);

    @Update("UPDATE leave SET status = #{status} WHERE id = #{id}")
    void updateStatus(@Param("id") Long id, @Param("status") Integer status);

    @Update("<script>" +
            "UPDATE leave" +
            "<set>" +
            "<if test=\"title != null\">title = #{title},</if>" +
            "<if test=\"elderId != null\">elder_id = #{elderId},</if>" +
            "<if test=\"name != null\">name = #{name},</if>" +
            "<if test=\"idCardNo != null\">id_card_no = #{idCardNo},</if>" +
            "<if test=\"phone != null\">phone = #{phone},</if>" +
            "<if test=\"nursingLevelName != null\">nursing_level_name = #{nursingLevelName},</if>" +
            "<if test=\"bedNo != null\">bed_no = #{bedNo},</if>" +
            "<if test=\"counselor != null\">counselor = #{counselor},</if>" +
            "<if test=\"leaveStartTime != null\">leave_start_time = #{leaveStartTime},</if>" +
            "<if test=\"leaveEndTime != null\">leave_end_time = #{leaveEndTime},</if>" +
            "<if test=\"leaveReason != null\">leave_reason = #{leaveReason},</if>" +
            "<if test=\"remark != null\">remark = #{remark},</if>" +
            "<if test=\"applicat != null\">applicat = #{applicat},</if>" +
            "<if test=\"applicatId != null\">applicat_id = #{applicatId},</if>" +
            "<if test=\"createTime != null\">create_time = #{createTime},</if>" +
            "<if test=\"flowStatus != null\">flow_status = #{flowStatus},</if>" +
            "<if test=\"status != null\">status = #{status},</if>" +
            "</set>" +
            "WHERE leave_code = #{leaveCode}" +
            "</script>")
    void update(Leave leave);

    IPage<Leave> selectByPage(Page<Leave> page,
                              @Param("leaveCode") String leaveCode,
                              @Param("name") String name,
                              @Param("idCardNo") String idCardNo,
                              @Param("startTime") LocalDateTime startTime,
                              @Param("endTime") LocalDateTime endTime,
                              @Param("params") Map<String, Object> params);
}
