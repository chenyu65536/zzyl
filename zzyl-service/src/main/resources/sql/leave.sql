-- 请假表 (PostgreSQL)
CREATE TABLE leave (
    id BIGSERIAL PRIMARY KEY,
    leave_code VARCHAR(64) NOT NULL UNIQUE,
    title VARCHAR(128),
    elder_id BIGINT,
    name VARCHAR(64),
    id_card_no VARCHAR(32),
    phone VARCHAR(16),
    nursing_level_name VARCHAR(64),
    bed_no VARCHAR(32),
    counselor VARCHAR(64),
    leave_start_time TIMESTAMP,
    leave_end_time TIMESTAMP,
    leave_reason VARCHAR(512),
    remark VARCHAR(512),
    applicat VARCHAR(64),
    applicat_id BIGINT,
    dept_no VARCHAR(32),
    create_time TIMESTAMP,
    flow_status INT DEFAULT 0,
    status INT DEFAULT 1,
    create_by BIGINT
);

COMMENT ON TABLE leave IS '请假表';
COMMENT ON COLUMN leave.id IS '主键';
COMMENT ON COLUMN leave.leave_code IS '请假单号';
COMMENT ON COLUMN leave.title IS '请假标题';
COMMENT ON COLUMN leave.elder_id IS '老人id';
COMMENT ON COLUMN leave.name IS '老人姓名';
COMMENT ON COLUMN leave.id_card_no IS '身份证号';
COMMENT ON COLUMN leave.phone IS '联系方式';
COMMENT ON COLUMN leave.nursing_level_name IS '护理等级';
COMMENT ON COLUMN leave.bed_no IS '床位编号';
COMMENT ON COLUMN leave.counselor IS '养老顾问';
COMMENT ON COLUMN leave.leave_start_time IS '请假开始时间';
COMMENT ON COLUMN leave.leave_end_time IS '请假结束时间';
COMMENT ON COLUMN leave.leave_reason IS '请假原因';
COMMENT ON COLUMN leave.remark IS '备注';
COMMENT ON COLUMN leave.applicat IS '申请人';
COMMENT ON COLUMN leave.applicat_id IS '申请人id';
COMMENT ON COLUMN leave.dept_no IS '申请人部门编号';
COMMENT ON COLUMN leave.create_time IS '申请时间';
COMMENT ON COLUMN leave.flow_status IS '流程状态（0:申请请假, 1:护理组长审批）';
COMMENT ON COLUMN leave.status IS '状态（1:申请中, 2:已完成, 3:已关闭）';
COMMENT ON COLUMN leave.create_by IS '创建人id';
