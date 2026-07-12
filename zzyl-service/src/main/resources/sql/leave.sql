-- 请假表
CREATE TABLE leave (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    leave_code VARCHAR(64) NOT NULL UNIQUE COMMENT '请假单号',
    title VARCHAR(128) COMMENT '请假标题',
    elder_id BIGINT COMMENT '老人id',
    name VARCHAR(64) COMMENT '老人姓名',
    id_card_no VARCHAR(32) COMMENT '身份证号',
    phone VARCHAR(16) COMMENT '联系方式',
    nursing_level_name VARCHAR(64) COMMENT '护理等级',
    bed_no VARCHAR(32) COMMENT '床位编号',
    counselor VARCHAR(64) COMMENT '养老顾问',
    leave_start_time DATETIME COMMENT '请假开始时间',
    leave_end_time DATETIME COMMENT '请假结束时间',
    leave_reason VARCHAR(512) COMMENT '请假原因',
    remark VARCHAR(512) COMMENT '备注',
    applicat VARCHAR(64) COMMENT '申请人',
    applicat_id BIGINT COMMENT '申请人id',
    dept_no VARCHAR(32) COMMENT '申请人部门编号',
    create_time DATETIME COMMENT '申请时间',
    flow_status INT DEFAULT 0 COMMENT '流程状态（0:申请请假, 1:护理组长审批）',
    status INT DEFAULT 1 COMMENT '状态（1:申请中, 2:已完成, 3:已关闭）',
    create_by BIGINT COMMENT '创建人id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='请假表';
