package com.zzyl.entity;

import com.zzyl.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * 请假实体类
 */
@Data
@ApiModel(description = "请假实体类")
public class Leave extends BaseEntity {

    /**
     * 请假单号
     */
    @ApiModelProperty(value = "请假单号")
    private String leaveCode;

    /**
     * 请假标题
     */
    @ApiModelProperty(value = "请假标题")
    private String title;

    /**
     * 老人id
     */
    @ApiModelProperty(value = "老人id")
    private Long elderId;

    /**
     * 老人姓名
     */
    @ApiModelProperty(value = "老人姓名")
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idCardNo;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String phone;

    /**
     * 护理等级
     */
    @ApiModelProperty(value = "护理等级")
    private String nursingLevelName;

    /**
     * 床位编号
     */
    @ApiModelProperty(value = "床位编号")
    private String bedNo;

    /**
     * 养老顾问
     */
    @ApiModelProperty(value = "养老顾问")
    private String counselor;

    /**
     * 请假开始时间
     */
    @ApiModelProperty(value = "请假开始时间")
    private LocalDateTime leaveStartTime;

    /**
     * 请假结束时间
     */
    @ApiModelProperty(value = "请假结束时间")
    private LocalDateTime leaveEndTime;

    /**
     * 请假原因
     */
    @ApiModelProperty(value = "请假原因")
    private String leaveReason;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 申请人
     */
    @ApiModelProperty(value = "申请人")
    private String applicat;

    /**
     * 申请人部门编号
     */
    @ApiModelProperty(value = "申请人部门编号")
    private String deptNo;

    /**
     * 申请人id
     */
    @ApiModelProperty(value = "申请人id")
    private Long applicatId;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间")
    private LocalDateTime createTime;

    /**
     * 流程状态
     * 0:申请请假
     * 1:护理组长审批
     */
    @ApiModelProperty(value = "流程状态")
    private Integer flowStatus;

    /**
     * 状态（1：申请中，2:已完成,3:已关闭）
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    // 流程状态枚举
    @Alias("LeaveFlowStatus")
    public enum FlowStatus {
        APPLY(0, "申请请假"),
        NURSE_APPROVAL(1, "护理组长审批");

        Integer code;
        String name;

        FlowStatus(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }
    }

    // 状态枚举
    @Alias("LeaveStatus")
    public enum Status {
        APPLICATION(1),
        FINISHED(2),
        CLOSED(3);

        Integer code;

        Status(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return this.code;
        }
    }
}
