-- ============================================================
-- 智慧养老项目 (中州养老) 数据库建表语句
-- 数据库: PostgreSQL
-- 自动生成自 Entity 类
-- ============================================================

-- AccraditationRecord表 (PostgreSQL)
CREATE TABLE accraditation_record (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    opinion VARCHAR(255),
    type INTEGER,
    approver_id BIGINT,
    approver_name VARCHAR(255),
    approver_name_role VARCHAR(255),
    bussniess_id BIGINT,
    current_step VARCHAR(255),
    next_step VARCHAR(255),
    next_approver VARCHAR(255),
    next_approver_id BIGINT,
    next_approver_role VARCHAR(255),
    step_no BIGINT,
    handle_type INTEGER,
    audit_status INTEGER
);

COMMENT ON TABLE accraditation_record IS 'AccraditationRecord表';
COMMENT ON COLUMN accraditation_record.id IS '主键';
COMMENT ON COLUMN accraditation_record.create_time IS '创建时间';
COMMENT ON COLUMN accraditation_record.update_time IS '更新时间';
COMMENT ON COLUMN accraditation_record.create_by IS '创建人';
COMMENT ON COLUMN accraditation_record.update_by IS '更新人';
COMMENT ON COLUMN accraditation_record.remark IS '备注';
COMMENT ON COLUMN accraditation_record.creator IS '创建人姓名';
COMMENT ON COLUMN accraditation_record.updater IS '更新人姓名';
-- AlertRule表 (PostgreSQL)
CREATE TABLE alert_rule (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    alert_effective_period VARCHAR(255),
    alert_rule_name VARCHAR(255),
    alert_silent_period INTEGER,
    data_aggregation_period INTEGER,
    device_name VARCHAR(255),
    duration INTEGER,
    function_id VARCHAR(255),
    function_name VARCHAR(255),
    module_id VARCHAR(255),
    module_name VARCHAR(255),
    operator VARCHAR(255),
    product_id VARCHAR(255),
    product_name VARCHAR(255),
    related_device VARCHAR(255),
    statistic_field VARCHAR(255),
    status INTEGER DEFAULT 1,
    value REAL
);

COMMENT ON TABLE alert_rule IS 'AlertRule表';
COMMENT ON COLUMN alert_rule.id IS '主键';
COMMENT ON COLUMN alert_rule.create_time IS '创建时间';
COMMENT ON COLUMN alert_rule.update_time IS '更新时间';
COMMENT ON COLUMN alert_rule.create_by IS '创建人';
COMMENT ON COLUMN alert_rule.update_by IS '更新人';
COMMENT ON COLUMN alert_rule.remark IS '备注';
COMMENT ON COLUMN alert_rule.creator IS '创建人姓名';
COMMENT ON COLUMN alert_rule.updater IS '更新人姓名';
-- Applications表 (PostgreSQL)
CREATE TABLE applications (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    applicat VARCHAR(255),
    applicat_id BIGINT,
    application_time TIMESTAMP,
    code VARCHAR(255),
    finish_time TIMESTAMP,
    status INTEGER DEFAULT 1,
    title VARCHAR(128),
    type INTEGER
);

COMMENT ON TABLE applications IS 'Applications表';
COMMENT ON COLUMN applications.id IS '主键';
COMMENT ON COLUMN applications.create_time IS '创建时间';
COMMENT ON COLUMN applications.update_time IS '更新时间';
COMMENT ON COLUMN applications.create_by IS '创建人';
COMMENT ON COLUMN applications.update_by IS '更新人';
COMMENT ON COLUMN applications.remark IS '备注';
COMMENT ON COLUMN applications.creator IS '创建人姓名';
COMMENT ON COLUMN applications.updater IS '更新人姓名';
-- Balance表 (PostgreSQL)
CREATE TABLE balance (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    payment_deadline TIMESTAMP,
    prepaid_balance NUMERIC(19,2),
    deposit_amount NUMERIC(19,2),
    arrears_amount NUMERIC(19,2),
    status INTEGER DEFAULT 1,
    elder_id BIGINT,
    elder_name VARCHAR(255),
    bed_no VARCHAR(32)
);

COMMENT ON TABLE balance IS 'Balance表';
COMMENT ON COLUMN balance.id IS '主键';
COMMENT ON COLUMN balance.create_time IS '创建时间';
COMMENT ON COLUMN balance.update_time IS '更新时间';
COMMENT ON COLUMN balance.create_by IS '创建人';
COMMENT ON COLUMN balance.update_by IS '更新人';
COMMENT ON COLUMN balance.remark IS '备注';
COMMENT ON COLUMN balance.creator IS '创建人姓名';
COMMENT ON COLUMN balance.updater IS '更新人姓名';
-- Bed表 (PostgreSQL)
CREATE TABLE bed (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    bed_number VARCHAR(32),
    bed_status INTEGER,
    room_id BIGINT,
    sort INTEGER
);

COMMENT ON TABLE bed IS 'Bed表';
COMMENT ON COLUMN bed.id IS '主键';
COMMENT ON COLUMN bed.create_time IS '创建时间';
COMMENT ON COLUMN bed.update_time IS '更新时间';
COMMENT ON COLUMN bed.create_by IS '创建人';
COMMENT ON COLUMN bed.update_by IS '更新人';
COMMENT ON COLUMN bed.remark IS '备注';
COMMENT ON COLUMN bed.creator IS '创建人姓名';
COMMENT ON COLUMN bed.updater IS '更新人姓名';
-- Bill表 (PostgreSQL)
CREATE TABLE bill (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    payment_deadline TIMESTAMP,
    bill_start_time TIMESTAMP,
    bill_end_time TIMESTAMP,
    lname VARCHAR(255),
    type_name VARCHAR(255),
    total NUMERIC(19,2),
    bill_no VARCHAR(255),
    bill_type INTEGER,
    bill_month VARCHAR(255),
    elder_id BIGINT,
    bill_amount NUMERIC(19,2),
    payable_amount NUMERIC(19,2),
    transaction_status INTEGER,
    total_days INTEGER,
    current_cost NUMERIC(19,2),
    deposit_amount NUMERIC(19,2),
    prepaid_amount NUMERIC(19,2),
    trading_order_no BIGINT,
    elder_vo VARCHAR(255),
    check_in_config_vo VARCHAR(255),
    bed_vo VARCHAR(255)
);

COMMENT ON TABLE bill IS 'Bill表';
COMMENT ON COLUMN bill.id IS '主键';
COMMENT ON COLUMN bill.create_time IS '创建时间';
COMMENT ON COLUMN bill.update_time IS '更新时间';
COMMENT ON COLUMN bill.create_by IS '创建人';
COMMENT ON COLUMN bill.update_by IS '更新人';
COMMENT ON COLUMN bill.remark IS '备注';
COMMENT ON COLUMN bill.creator IS '创建人姓名';
COMMENT ON COLUMN bill.updater IS '更新人姓名';
-- CheckIn表 (PostgreSQL)
CREATE TABLE check_in (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    check_in_code VARCHAR(255),
    title VARCHAR(128),
    elder_dto VARCHAR(255),
    other_apply_info VARCHAR(255),
    review_info VARCHAR(255),
    elder_id BIGINT,
    counselor VARCHAR(255),
    check_in_time TIMESTAMP,
    reason VARCHAR(255),
    applicat VARCHAR(255),
    dept_no VARCHAR(255),
    applicat_id BIGINT,
    flow_status INTEGER,
    status INTEGER DEFAULT 1,
    elder_vo VARCHAR(255),
    room_vo VARCHAR(255),
    check_in_config_vo VARCHAR(255),
    nursing_level_vo VARCHAR(255),
    bed_vo VARCHAR(255)
);

COMMENT ON TABLE check_in IS 'CheckIn表';
COMMENT ON COLUMN check_in.id IS '主键';
COMMENT ON COLUMN check_in.create_time IS '创建时间';
COMMENT ON COLUMN check_in.update_time IS '更新时间';
COMMENT ON COLUMN check_in.create_by IS '创建人';
COMMENT ON COLUMN check_in.update_by IS '更新人';
COMMENT ON COLUMN check_in.remark IS '备注';
COMMENT ON COLUMN check_in.creator IS '创建人姓名';
COMMENT ON COLUMN check_in.updater IS '更新人姓名';
-- CheckInConfig表 (PostgreSQL)
CREATE TABLE check_in_config (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    check_in_start_time TIMESTAMP,
    check_in_end_time TIMESTAMP,
    cost_start_time TIMESTAMP,
    cost_end_time TIMESTAMP,
    elder_id BIGINT,
    nursing_level_id BIGINT,
    bed_no VARCHAR(32),
    deposit_amount NUMERIC(19,2),
    nursing_cost NUMERIC(19,2),
    bed_cost NUMERIC(19,2),
    other_cost NUMERIC(19,2),
    medical_insurance_payment NUMERIC(19,2),
    government_subsidy NUMERIC(19,2),
    nursing_level VARCHAR(255)
);

COMMENT ON TABLE check_in_config IS 'CheckInConfig表';
COMMENT ON COLUMN check_in_config.id IS '主键';
COMMENT ON COLUMN check_in_config.create_time IS '创建时间';
COMMENT ON COLUMN check_in_config.update_time IS '更新时间';
COMMENT ON COLUMN check_in_config.create_by IS '创建人';
COMMENT ON COLUMN check_in_config.update_by IS '更新人';
COMMENT ON COLUMN check_in_config.remark IS '备注';
COMMENT ON COLUMN check_in_config.creator IS '创建人姓名';
COMMENT ON COLUMN check_in_config.updater IS '更新人姓名';
-- Contract表 (PostgreSQL)
CREATE TABLE contract (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    sign_date TIMESTAMP,
    release_date TIMESTAMP,
    name VARCHAR(64),
    member_phone VARCHAR(255),
    member_name VARCHAR(255),
    elder_name VARCHAR(255),
    contract_no VARCHAR(255),
    pdf_url VARCHAR(255),
    member_id BIGINT,
    elder_id BIGINT,
    status INTEGER DEFAULT 1,
    sort INTEGER,
    level_desc VARCHAR(255),
    check_in_no VARCHAR(255),
    release_submitter VARCHAR(255),
    release_pdf_url VARCHAR(255),
    elder_vo VARCHAR(255),
    room_vo VARCHAR(255)
);

COMMENT ON TABLE contract IS 'Contract表';
COMMENT ON COLUMN contract.id IS '主键';
COMMENT ON COLUMN contract.create_time IS '创建时间';
COMMENT ON COLUMN contract.update_time IS '更新时间';
COMMENT ON COLUMN contract.create_by IS '创建人';
COMMENT ON COLUMN contract.update_by IS '更新人';
COMMENT ON COLUMN contract.remark IS '备注';
COMMENT ON COLUMN contract.creator IS '创建人姓名';
COMMENT ON COLUMN contract.updater IS '更新人姓名';
-- Dept表 (PostgreSQL)
CREATE TABLE dept (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    parent_dept_no VARCHAR(255),
    dept_no VARCHAR(255),
    dept_name VARCHAR(255),
    sort_no INTEGER,
    data_state VARCHAR(255),
    leader_id BIGINT,
    leader_name VARCHAR(255)
);

COMMENT ON TABLE dept IS 'Dept表';
COMMENT ON COLUMN dept.id IS '主键';
COMMENT ON COLUMN dept.create_time IS '创建时间';
COMMENT ON COLUMN dept.update_time IS '更新时间';
COMMENT ON COLUMN dept.create_by IS '创建人';
COMMENT ON COLUMN dept.update_by IS '更新人';
COMMENT ON COLUMN dept.remark IS '备注';
COMMENT ON COLUMN dept.creator IS '创建人姓名';
COMMENT ON COLUMN dept.updater IS '更新人姓名';
-- Device表 (PostgreSQL)
CREATE TABLE device (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    nickname VARCHAR(255),
    node_type INTEGER,
    owner BOOLEAN,
    product_key VARCHAR(255),
    product_name VARCHAR(255),
    region VARCHAR(255),
    status INTEGER DEFAULT 1,
    utc_active VARCHAR(255),
    utc_create VARCHAR(255),
    utc_online VARCHAR(255),
    device_id VARCHAR(255),
    binding_location VARCHAR(255),
    location_type INTEGER,
    physical_location_type INTEGER,
    device_name VARCHAR(255),
    device_description VARCHAR(255),
    is_deleted BOOLEAN,
    note_name VARCHAR(255),
    product_id VARCHAR(255),
    device_data_vos VARCHAR(255)
);

COMMENT ON TABLE device IS 'Device表';
COMMENT ON COLUMN device.id IS '主键';
COMMENT ON COLUMN device.create_time IS '创建时间';
COMMENT ON COLUMN device.update_time IS '更新时间';
COMMENT ON COLUMN device.create_by IS '创建人';
COMMENT ON COLUMN device.update_by IS '更新人';
COMMENT ON COLUMN device.remark IS '备注';
COMMENT ON COLUMN device.creator IS '创建人姓名';
COMMENT ON COLUMN device.updater IS '更新人姓名';
-- DeviceData表 (PostgreSQL)
CREATE TABLE device_data (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    access_location VARCHAR(255),
    alarm_time TIMESTAMP,
    data_value VARCHAR(255),
    device_name VARCHAR(255),
    function_name VARCHAR(255),
    iot_id VARCHAR(255),
    note_name VARCHAR(255),
    processing_result VARCHAR(255),
    processing_time TIMESTAMP,
    processor VARCHAR(255),
    product_id VARCHAR(255),
    product_name VARCHAR(255),
    status INTEGER DEFAULT 1
);

COMMENT ON TABLE device_data IS 'DeviceData表';
COMMENT ON COLUMN device_data.id IS '主键';
COMMENT ON COLUMN device_data.create_time IS '创建时间';
COMMENT ON COLUMN device_data.update_time IS '更新时间';
COMMENT ON COLUMN device_data.create_by IS '创建人';
COMMENT ON COLUMN device_data.update_by IS '更新人';
COMMENT ON COLUMN device_data.remark IS '备注';
COMMENT ON COLUMN device_data.creator IS '创建人姓名';
COMMENT ON COLUMN device_data.updater IS '更新人姓名';
-- Elder表 (PostgreSQL)
CREATE TABLE elder (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    age VARCHAR(255),
    sex VARCHAR(255),
    name VARCHAR(64),
    image VARCHAR(512),
    status INTEGER DEFAULT 1,
    id_card_no VARCHAR(32),
    phone VARCHAR(20),
    bed_number VARCHAR(32),
    bed_id BIGINT
);

COMMENT ON TABLE elder IS 'Elder表';
COMMENT ON COLUMN elder.id IS '主键';
COMMENT ON COLUMN elder.create_time IS '创建时间';
COMMENT ON COLUMN elder.update_time IS '更新时间';
COMMENT ON COLUMN elder.create_by IS '创建人';
COMMENT ON COLUMN elder.update_by IS '更新人';
COMMENT ON COLUMN elder.remark IS '备注';
COMMENT ON COLUMN elder.creator IS '创建人姓名';
COMMENT ON COLUMN elder.updater IS '更新人姓名';
-- Floor表 (PostgreSQL)
CREATE TABLE floor (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    code INTEGER
);

COMMENT ON TABLE floor IS 'Floor表';
COMMENT ON COLUMN floor.id IS '主键';
COMMENT ON COLUMN floor.create_time IS '创建时间';
COMMENT ON COLUMN floor.update_time IS '更新时间';
COMMENT ON COLUMN floor.create_by IS '创建人';
COMMENT ON COLUMN floor.update_by IS '更新人';
COMMENT ON COLUMN floor.remark IS '备注';
COMMENT ON COLUMN floor.creator IS '创建人姓名';
COMMENT ON COLUMN floor.updater IS '更新人姓名';
-- FlowInfo表 (PostgreSQL)
CREATE TABLE flow_info (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    flowname VARCHAR(255),
    flowkey VARCHAR(255),
    filepath VARCHAR(255),
    state INTEGER,
    createtime TIMESTAMP
);

COMMENT ON TABLE flow_info IS 'FlowInfo表';
COMMENT ON COLUMN flow_info.id IS '主键';
COMMENT ON COLUMN flow_info.create_time IS '创建时间';
COMMENT ON COLUMN flow_info.update_time IS '更新时间';
COMMENT ON COLUMN flow_info.create_by IS '创建人';
COMMENT ON COLUMN flow_info.update_by IS '更新人';
COMMENT ON COLUMN flow_info.remark IS '备注';
COMMENT ON COLUMN flow_info.creator IS '创建人姓名';
COMMENT ON COLUMN flow_info.updater IS '更新人姓名';
-- FundFlow表 (PostgreSQL)
CREATE TABLE fund_flow (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    balance_type INTEGER,
    fund_direction INTEGER,
    related_bill_no VARCHAR(255),
    flow_reason VARCHAR(255),
    amount NUMERIC(19,2),
    elder_id BIGINT,
    elder_name VARCHAR(255),
    bed_no VARCHAR(32)
);

COMMENT ON TABLE fund_flow IS 'FundFlow表';
COMMENT ON COLUMN fund_flow.id IS '主键';
COMMENT ON COLUMN fund_flow.create_time IS '创建时间';
COMMENT ON COLUMN fund_flow.update_time IS '更新时间';
COMMENT ON COLUMN fund_flow.create_by IS '创建人';
COMMENT ON COLUMN fund_flow.update_by IS '更新人';
COMMENT ON COLUMN fund_flow.remark IS '备注';
COMMENT ON COLUMN fund_flow.creator IS '创建人姓名';
COMMENT ON COLUMN fund_flow.updater IS '更新人姓名';
-- Leave表 (PostgreSQL)
CREATE TABLE leave (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    leave_code VARCHAR(64) NOT NULL UNIQUE,
    title VARCHAR(128),
    elder_id BIGINT,
    name VARCHAR(64),
    id_card_no VARCHAR(32),
    phone VARCHAR(20),
    nursing_level_name VARCHAR(255),
    bed_no VARCHAR(32),
    counselor VARCHAR(255),
    leave_start_time TIMESTAMP,
    leave_end_time TIMESTAMP,
    leave_reason VARCHAR(255),
    applicat VARCHAR(255),
    dept_no VARCHAR(255),
    applicat_id BIGINT,
    flow_status INTEGER,
    status INTEGER DEFAULT 1,
    task_id VARCHAR(255)
);

COMMENT ON TABLE leave IS 'Leave表';
COMMENT ON COLUMN leave.id IS '主键';
COMMENT ON COLUMN leave.create_time IS '创建时间';
COMMENT ON COLUMN leave.update_time IS '更新时间';
COMMENT ON COLUMN leave.create_by IS '创建人';
COMMENT ON COLUMN leave.update_by IS '更新人';
COMMENT ON COLUMN leave.remark IS '备注';
COMMENT ON COLUMN leave.creator IS '创建人姓名';
COMMENT ON COLUMN leave.updater IS '更新人姓名';
-- Member表 (PostgreSQL)
CREATE TABLE member (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    phone VARCHAR(20),
    name VARCHAR(64),
    avatar VARCHAR(512),
    open_id VARCHAR(128),
    gender VARCHAR(8)
);

COMMENT ON TABLE member IS 'Member表';
COMMENT ON COLUMN member.id IS '主键';
COMMENT ON COLUMN member.create_time IS '创建时间';
COMMENT ON COLUMN member.update_time IS '更新时间';
COMMENT ON COLUMN member.create_by IS '创建人';
COMMENT ON COLUMN member.update_by IS '更新人';
COMMENT ON COLUMN member.remark IS '备注';
COMMENT ON COLUMN member.creator IS '创建人姓名';
COMMENT ON COLUMN member.updater IS '更新人姓名';
-- MemberElder表 (PostgreSQL)
CREATE TABLE member_elder (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    device_vos VARCHAR(255),
    member_id BIGINT,
    elder_id BIGINT,
    elder_vo VARCHAR(255),
    bed_vo VARCHAR(255),
    room_vo VARCHAR(255)
);

COMMENT ON TABLE member_elder IS 'MemberElder表';
COMMENT ON COLUMN member_elder.id IS '主键';
COMMENT ON COLUMN member_elder.create_time IS '创建时间';
COMMENT ON COLUMN member_elder.update_time IS '更新时间';
COMMENT ON COLUMN member_elder.create_by IS '创建人';
COMMENT ON COLUMN member_elder.update_by IS '更新人';
COMMENT ON COLUMN member_elder.remark IS '备注';
COMMENT ON COLUMN member_elder.creator IS '创建人姓名';
COMMENT ON COLUMN member_elder.updater IS '更新人姓名';
-- NursingElder表 (PostgreSQL)
CREATE TABLE nursing_elder (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    nursing_id BIGINT,
    elder_id BIGINT
);

COMMENT ON TABLE nursing_elder IS 'NursingElder表';
COMMENT ON COLUMN nursing_elder.id IS '主键';
COMMENT ON COLUMN nursing_elder.create_time IS '创建时间';
COMMENT ON COLUMN nursing_elder.update_time IS '更新时间';
COMMENT ON COLUMN nursing_elder.create_by IS '创建人';
COMMENT ON COLUMN nursing_elder.update_by IS '更新人';
COMMENT ON COLUMN nursing_elder.remark IS '备注';
COMMENT ON COLUMN nursing_elder.creator IS '创建人姓名';
COMMENT ON COLUMN nursing_elder.updater IS '更新人姓名';
-- NursingLevel表 (PostgreSQL)
CREATE TABLE nursing_level (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    plan_name VARCHAR(255),
    plan_id BIGINT,
    fee NUMERIC(10,2),
    status INTEGER DEFAULT 1,
    description VARCHAR(512)
);

COMMENT ON TABLE nursing_level IS 'NursingLevel表';
COMMENT ON COLUMN nursing_level.id IS '主键';
COMMENT ON COLUMN nursing_level.create_time IS '创建时间';
COMMENT ON COLUMN nursing_level.update_time IS '更新时间';
COMMENT ON COLUMN nursing_level.create_by IS '创建人';
COMMENT ON COLUMN nursing_level.update_by IS '更新人';
COMMENT ON COLUMN nursing_level.remark IS '备注';
COMMENT ON COLUMN nursing_level.creator IS '创建人姓名';
COMMENT ON COLUMN nursing_level.updater IS '更新人姓名';
-- NursingPlan表 (PostgreSQL)
CREATE TABLE nursing_plan (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    sort_no INTEGER,
    plan_name VARCHAR(255),
    status INTEGER DEFAULT 1,
    lid BIGINT
);

COMMENT ON TABLE nursing_plan IS 'NursingPlan表';
COMMENT ON COLUMN nursing_plan.id IS '主键';
COMMENT ON COLUMN nursing_plan.create_time IS '创建时间';
COMMENT ON COLUMN nursing_plan.update_time IS '更新时间';
COMMENT ON COLUMN nursing_plan.create_by IS '创建人';
COMMENT ON COLUMN nursing_plan.update_by IS '更新人';
COMMENT ON COLUMN nursing_plan.remark IS '备注';
COMMENT ON COLUMN nursing_plan.creator IS '创建人姓名';
COMMENT ON COLUMN nursing_plan.updater IS '更新人姓名';
-- NursingProject表 (PostgreSQL)
CREATE TABLE nursing_project (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    order_no INTEGER,
    unit VARCHAR(255),
    price NUMERIC(10,2),
    image VARCHAR(512),
    nursing_requirement VARCHAR(255),
    status INTEGER DEFAULT 1
);

COMMENT ON TABLE nursing_project IS 'NursingProject表';
COMMENT ON COLUMN nursing_project.id IS '主键';
COMMENT ON COLUMN nursing_project.create_time IS '创建时间';
COMMENT ON COLUMN nursing_project.update_time IS '更新时间';
COMMENT ON COLUMN nursing_project.create_by IS '创建人';
COMMENT ON COLUMN nursing_project.update_by IS '更新人';
COMMENT ON COLUMN nursing_project.remark IS '备注';
COMMENT ON COLUMN nursing_project.creator IS '创建人姓名';
COMMENT ON COLUMN nursing_project.updater IS '更新人姓名';
-- NursingProjectPlan表 (PostgreSQL)
CREATE TABLE nursing_project_plan (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    plan_id BIGINT,
    project_id BIGINT,
    execute_time VARCHAR(255),
    execute_cycle INTEGER,
    execute_frequency INTEGER
);

COMMENT ON TABLE nursing_project_plan IS 'NursingProjectPlan表';
COMMENT ON COLUMN nursing_project_plan.id IS '主键';
COMMENT ON COLUMN nursing_project_plan.create_time IS '创建时间';
COMMENT ON COLUMN nursing_project_plan.update_time IS '更新时间';
COMMENT ON COLUMN nursing_project_plan.create_by IS '创建人';
COMMENT ON COLUMN nursing_project_plan.update_by IS '更新人';
COMMENT ON COLUMN nursing_project_plan.remark IS '备注';
COMMENT ON COLUMN nursing_project_plan.creator IS '创建人姓名';
COMMENT ON COLUMN nursing_project_plan.updater IS '更新人姓名';
-- NursingTask表 (PostgreSQL)
CREATE TABLE nursing_task (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    nursing_id BIGINT,
    project_id BIGINT,
    elder_id BIGINT,
    bed_number VARCHAR(32),
    task_type SMALLINT,
    estimated_server_time TIMESTAMP,
    mark VARCHAR(255),
    cancel_reason VARCHAR(255),
    status INTEGER DEFAULT 1,
    rel_no VARCHAR(255),
    task_image VARCHAR(255),
    project_name VARCHAR(255),
    elder_name VARCHAR(255),
    age VARCHAR(255),
    image VARCHAR(512),
    sex VARCHAR(255),
    nursing_name VARCHAR(255),
    l_name VARCHAR(255)
);

COMMENT ON TABLE nursing_task IS 'NursingTask表';
COMMENT ON COLUMN nursing_task.id IS '主键';
COMMENT ON COLUMN nursing_task.create_time IS '创建时间';
COMMENT ON COLUMN nursing_task.update_time IS '更新时间';
COMMENT ON COLUMN nursing_task.create_by IS '创建人';
COMMENT ON COLUMN nursing_task.update_by IS '更新人';
COMMENT ON COLUMN nursing_task.remark IS '备注';
COMMENT ON COLUMN nursing_task.creator IS '创建人姓名';
COMMENT ON COLUMN nursing_task.updater IS '更新人姓名';
-- Order表 (PostgreSQL)
CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    trading_order_no BIGINT,
    payment_status INTEGER,
    amount NUMERIC(19,2),
    refund NUMERIC(19,2),
    is_refund VARCHAR(255),
    member_id BIGINT,
    project_id BIGINT,
    elder_id BIGINT,
    estimated_arrival_time TIMESTAMP,
    order_no VARCHAR(255),
    reason VARCHAR(255),
    status INTEGER DEFAULT 1,
    view_status INTEGER DEFAULT 0,
    o_create_type INTEGER
);

COMMENT ON TABLE orders IS 'Order表';
COMMENT ON COLUMN orders.id IS '主键';
COMMENT ON COLUMN orders.create_time IS '创建时间';
COMMENT ON COLUMN orders.update_time IS '更新时间';
COMMENT ON COLUMN orders.create_by IS '创建人';
COMMENT ON COLUMN orders.update_by IS '更新人';
COMMENT ON COLUMN orders.remark IS '备注';
COMMENT ON COLUMN orders.creator IS '创建人姓名';
COMMENT ON COLUMN orders.updater IS '更新人姓名';
-- PendingTasks表 (PostgreSQL)
CREATE TABLE pending_tasks (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    applicat VARCHAR(255),
    applicat_id BIGINT,
    application_time TIMESTAMP,
    code VARCHAR(255),
    status INTEGER DEFAULT 1,
    assignee_id BIGINT,
    assignee VARCHAR(255),
    title VARCHAR(128),
    type INTEGER,
    finish_time TIMESTAMP,
    step_no INTEGER,
    flow_status INTEGER,
    check_in_id BIGINT,
    is_handle INTEGER
);

COMMENT ON TABLE pending_tasks IS 'PendingTasks表';
COMMENT ON COLUMN pending_tasks.id IS '主键';
COMMENT ON COLUMN pending_tasks.create_time IS '创建时间';
COMMENT ON COLUMN pending_tasks.update_time IS '更新时间';
COMMENT ON COLUMN pending_tasks.create_by IS '创建人';
COMMENT ON COLUMN pending_tasks.update_by IS '更新人';
COMMENT ON COLUMN pending_tasks.remark IS '备注';
COMMENT ON COLUMN pending_tasks.creator IS '创建人姓名';
COMMENT ON COLUMN pending_tasks.updater IS '更新人姓名';
-- Post表 (PostgreSQL)
CREATE TABLE post (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    dept_no VARCHAR(255),
    post_no VARCHAR(255),
    post_name VARCHAR(255),
    sort_no INTEGER,
    data_state VARCHAR(255)
);

COMMENT ON TABLE post IS 'Post表';
COMMENT ON COLUMN post.id IS '主键';
COMMENT ON COLUMN post.create_time IS '创建时间';
COMMENT ON COLUMN post.update_time IS '更新时间';
COMMENT ON COLUMN post.create_by IS '创建人';
COMMENT ON COLUMN post.update_by IS '更新人';
COMMENT ON COLUMN post.remark IS '备注';
COMMENT ON COLUMN post.creator IS '创建人姓名';
COMMENT ON COLUMN post.updater IS '更新人姓名';
-- PrepaidRechargeRecord表 (PostgreSQL)
CREATE TABLE prepaid_recharge_record (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    recharge_amount NUMERIC(19,2),
    recharge_voucher VARCHAR(255),
    recharge_method VARCHAR(255),
    elder_id BIGINT,
    elder_name VARCHAR(255),
    bed_no VARCHAR(32),
    prepaid_recharge_no VARCHAR(255)
);

COMMENT ON TABLE prepaid_recharge_record IS 'PrepaidRechargeRecord表';
COMMENT ON COLUMN prepaid_recharge_record.id IS '主键';
COMMENT ON COLUMN prepaid_recharge_record.create_time IS '创建时间';
COMMENT ON COLUMN prepaid_recharge_record.update_time IS '更新时间';
COMMENT ON COLUMN prepaid_recharge_record.create_by IS '创建人';
COMMENT ON COLUMN prepaid_recharge_record.update_by IS '更新人';
COMMENT ON COLUMN prepaid_recharge_record.remark IS '备注';
COMMENT ON COLUMN prepaid_recharge_record.creator IS '创建人姓名';
COMMENT ON COLUMN prepaid_recharge_record.updater IS '更新人姓名';
-- RefundRecord表 (PostgreSQL)
CREATE TABLE refund_record (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    trading_order_no BIGINT,
    product_order_no BIGINT,
    refund_no BIGINT,
    enterprise_id BIGINT,
    trading_channel VARCHAR(255),
    refund_status INTEGER,
    refund_code VARCHAR(255),
    refund_msg VARCHAR(255),
    memo VARCHAR(255),
    refund_amount NUMERIC(19,2),
    total NUMERIC(19,2),
    data_state VARCHAR(255),
    create_type INTEGER
);

COMMENT ON TABLE refund_record IS 'RefundRecord表';
COMMENT ON COLUMN refund_record.id IS '主键';
COMMENT ON COLUMN refund_record.create_time IS '创建时间';
COMMENT ON COLUMN refund_record.update_time IS '更新时间';
COMMENT ON COLUMN refund_record.create_by IS '创建人';
COMMENT ON COLUMN refund_record.update_by IS '更新人';
COMMENT ON COLUMN refund_record.remark IS '备注';
COMMENT ON COLUMN refund_record.creator IS '创建人姓名';
COMMENT ON COLUMN refund_record.updater IS '更新人姓名';
-- RescissionContract表 (PostgreSQL)
CREATE TABLE rescission_contract (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    contract_url VARCHAR(255),
    rescission_contract_name VARCHAR(255),
    relieve_time TIMESTAMP,
    commitor VARCHAR(255)
);

COMMENT ON TABLE rescission_contract IS 'RescissionContract表';
COMMENT ON COLUMN rescission_contract.id IS '主键';
COMMENT ON COLUMN rescission_contract.create_time IS '创建时间';
COMMENT ON COLUMN rescission_contract.update_time IS '更新时间';
COMMENT ON COLUMN rescission_contract.create_by IS '创建人';
COMMENT ON COLUMN rescission_contract.update_by IS '更新人';
COMMENT ON COLUMN rescission_contract.remark IS '备注';
COMMENT ON COLUMN rescission_contract.creator IS '创建人姓名';
COMMENT ON COLUMN rescission_contract.updater IS '更新人姓名';
-- Reservation表 (PostgreSQL)
CREATE TABLE reservation (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    mobile VARCHAR(255),
    time TIMESTAMP,
    visitor VARCHAR(255),
    type INTEGER,
    status INTEGER DEFAULT 1
);

COMMENT ON TABLE reservation IS 'Reservation表';
COMMENT ON COLUMN reservation.id IS '主键';
COMMENT ON COLUMN reservation.create_time IS '创建时间';
COMMENT ON COLUMN reservation.update_time IS '更新时间';
COMMENT ON COLUMN reservation.create_by IS '创建人';
COMMENT ON COLUMN reservation.update_by IS '更新人';
COMMENT ON COLUMN reservation.remark IS '备注';
COMMENT ON COLUMN reservation.creator IS '创建人姓名';
COMMENT ON COLUMN reservation.updater IS '更新人姓名';
-- Resource表 (PostgreSQL)
CREATE TABLE resource (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    data_state VARCHAR(255),
    icon VARCHAR(255),
    label VARCHAR(255),
    parent_resource_no VARCHAR(255),
    request_path VARCHAR(255),
    resource_name VARCHAR(255),
    resource_no VARCHAR(255),
    resource_type VARCHAR(255),
    sort_no INTEGER
);

COMMENT ON TABLE resource IS 'Resource表';
COMMENT ON COLUMN resource.id IS '主键';
COMMENT ON COLUMN resource.create_time IS '创建时间';
COMMENT ON COLUMN resource.update_time IS '更新时间';
COMMENT ON COLUMN resource.create_by IS '创建人';
COMMENT ON COLUMN resource.update_by IS '更新人';
COMMENT ON COLUMN resource.remark IS '备注';
COMMENT ON COLUMN resource.creator IS '创建人姓名';
COMMENT ON COLUMN resource.updater IS '更新人姓名';
-- Retreat表 (PostgreSQL)
CREATE TABLE retreat (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    retreat_code VARCHAR(255),
    title VARCHAR(128),
    elder_id BIGINT,
    name VARCHAR(64),
    id_card_no VARCHAR(32),
    phone VARCHAR(20),
    check_in_start_time TIMESTAMP,
    check_in_end_time TIMESTAMP,
    nursing_level_name VARCHAR(255),
    bed_no VARCHAR(32),
    contract_name VARCHAR(255),
    contract_url VARCHAR(255),
    contract_no VARCHAR(255),
    counselor VARCHAR(255),
    check_out_time TIMESTAMP,
    reason VARCHAR(255),
    applicat VARCHAR(255),
    dept_no VARCHAR(255),
    applicat_id BIGINT,
    flow_status INTEGER,
    status INTEGER DEFAULT 1,
    task_id VARCHAR(255),
    nursing_name VARCHAR(255)
);

COMMENT ON TABLE retreat IS 'Retreat表';
COMMENT ON COLUMN retreat.id IS '主键';
COMMENT ON COLUMN retreat.create_time IS '创建时间';
COMMENT ON COLUMN retreat.update_time IS '更新时间';
COMMENT ON COLUMN retreat.create_by IS '创建人';
COMMENT ON COLUMN retreat.update_by IS '更新人';
COMMENT ON COLUMN retreat.remark IS '备注';
COMMENT ON COLUMN retreat.creator IS '创建人姓名';
COMMENT ON COLUMN retreat.updater IS '更新人姓名';
-- RetreatBill表 (PostgreSQL)
CREATE TABLE retreat_bill (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    bill_json VARCHAR(255),
    elder_id BIGINT,
    is_refund INTEGER,
    refund_amount NUMERIC(19,2),
    refund_voucher_url VARCHAR(255),
    retreat_id BIGINT,
    trading_channel VARCHAR(255)
);

COMMENT ON TABLE retreat_bill IS 'RetreatBill表';
COMMENT ON COLUMN retreat_bill.id IS '主键';
COMMENT ON COLUMN retreat_bill.create_time IS '创建时间';
COMMENT ON COLUMN retreat_bill.update_time IS '更新时间';
COMMENT ON COLUMN retreat_bill.create_by IS '创建人';
COMMENT ON COLUMN retreat_bill.update_by IS '更新人';
COMMENT ON COLUMN retreat_bill.remark IS '备注';
COMMENT ON COLUMN retreat_bill.creator IS '创建人姓名';
COMMENT ON COLUMN retreat_bill.updater IS '更新人姓名';
-- Role表 (PostgreSQL)
CREATE TABLE role (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    data_scope VARCHAR(255),
    data_state VARCHAR(255),
    label VARCHAR(255),
    role_name VARCHAR(255),
    sort_no INTEGER
);

COMMENT ON TABLE role IS 'Role表';
COMMENT ON COLUMN role.id IS '主键';
COMMENT ON COLUMN role.create_time IS '创建时间';
COMMENT ON COLUMN role.update_time IS '更新时间';
COMMENT ON COLUMN role.create_by IS '创建人';
COMMENT ON COLUMN role.update_by IS '更新人';
COMMENT ON COLUMN role.remark IS '备注';
COMMENT ON COLUMN role.creator IS '创建人姓名';
COMMENT ON COLUMN role.updater IS '更新人姓名';
-- RoleDept表 (PostgreSQL)
CREATE TABLE role_dept (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    data_state VARCHAR(255),
    dept_no VARCHAR(255),
    role_id BIGINT
);

COMMENT ON TABLE role_dept IS 'RoleDept表';
COMMENT ON COLUMN role_dept.id IS '主键';
COMMENT ON COLUMN role_dept.create_time IS '创建时间';
COMMENT ON COLUMN role_dept.update_time IS '更新时间';
COMMENT ON COLUMN role_dept.create_by IS '创建人';
COMMENT ON COLUMN role_dept.update_by IS '更新人';
COMMENT ON COLUMN role_dept.remark IS '备注';
COMMENT ON COLUMN role_dept.creator IS '创建人姓名';
COMMENT ON COLUMN role_dept.updater IS '更新人姓名';
-- RoleResource表 (PostgreSQL)
CREATE TABLE role_resource (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    data_state VARCHAR(255),
    resource_no VARCHAR(255),
    role_id BIGINT
);

COMMENT ON TABLE role_resource IS 'RoleResource表';
COMMENT ON COLUMN role_resource.id IS '主键';
COMMENT ON COLUMN role_resource.create_time IS '创建时间';
COMMENT ON COLUMN role_resource.update_time IS '更新时间';
COMMENT ON COLUMN role_resource.create_by IS '创建人';
COMMENT ON COLUMN role_resource.update_by IS '更新人';
COMMENT ON COLUMN role_resource.remark IS '备注';
COMMENT ON COLUMN role_resource.creator IS '创建人姓名';
COMMENT ON COLUMN role_resource.updater IS '更新人姓名';
-- Room表 (PostgreSQL)
CREATE TABLE room (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    code VARCHAR(255),
    sort INTEGER,
    type_name VARCHAR(255),
    floor_id BIGINT
);

COMMENT ON TABLE room IS 'Room表';
COMMENT ON COLUMN room.id IS '主键';
COMMENT ON COLUMN room.create_time IS '创建时间';
COMMENT ON COLUMN room.update_time IS '更新时间';
COMMENT ON COLUMN room.create_by IS '创建人';
COMMENT ON COLUMN room.update_by IS '更新人';
COMMENT ON COLUMN room.remark IS '备注';
COMMENT ON COLUMN room.creator IS '创建人姓名';
COMMENT ON COLUMN room.updater IS '更新人姓名';
-- RoomType表 (PostgreSQL)
CREATE TABLE room_type (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    bed_count INTEGER,
    price NUMERIC(10,2),
    introduction VARCHAR(255),
    photo VARCHAR(255),
    type_name VARCHAR(255),
    status INTEGER DEFAULT 1
);

COMMENT ON TABLE room_type IS 'RoomType表';
COMMENT ON COLUMN room_type.id IS '主键';
COMMENT ON COLUMN room_type.create_time IS '创建时间';
COMMENT ON COLUMN room_type.update_time IS '更新时间';
COMMENT ON COLUMN room_type.create_by IS '创建人';
COMMENT ON COLUMN room_type.update_by IS '更新人';
COMMENT ON COLUMN room_type.remark IS '备注';
COMMENT ON COLUMN room_type.creator IS '创建人姓名';
COMMENT ON COLUMN room_type.updater IS '更新人姓名';
-- Trading表 (PostgreSQL)
CREATE TABLE trading (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    open_id VARCHAR(128),
    product_order_no BIGINT,
    trading_order_no BIGINT,
    trading_channel VARCHAR(255),
    trading_type VARCHAR(255),
    trading_state INTEGER,
    payee_name VARCHAR(255),
    payee_id BIGINT,
    payer_name VARCHAR(255),
    payer_id BIGINT,
    trading_amount NUMERIC(19,2),
    refund NUMERIC(19,2),
    is_refund VARCHAR(255),
    result_code VARCHAR(255),
    result_msg VARCHAR(255),
    result_json VARCHAR(255),
    place_order_code VARCHAR(255),
    place_order_msg VARCHAR(255),
    place_order_json VARCHAR(255),
    enterprise_id BIGINT,
    memo VARCHAR(255),
    qr_code VARCHAR(255),
    enable_flag VARCHAR(255),
    member_creator VARCHAR(255)
);

COMMENT ON TABLE trading IS 'Trading表';
COMMENT ON COLUMN trading.id IS '主键';
COMMENT ON COLUMN trading.create_time IS '创建时间';
COMMENT ON COLUMN trading.update_time IS '更新时间';
COMMENT ON COLUMN trading.create_by IS '创建人';
COMMENT ON COLUMN trading.update_by IS '更新人';
COMMENT ON COLUMN trading.remark IS '备注';
COMMENT ON COLUMN trading.creator IS '创建人姓名';
COMMENT ON COLUMN trading.updater IS '更新人姓名';
-- User表 (PostgreSQL) - 注意: user 是 PostgreSQL 保留字，表名使用 sys_user
CREATE TABLE sys_user (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    avatar VARCHAR(512),
    data_state VARCHAR(255),
    dept_no VARCHAR(255),
    email VARCHAR(128),
    is_delete INTEGER,
    is_leader INTEGER,
    mobile VARCHAR(255),
    nick_name VARCHAR(255),
    open_id VARCHAR(128),
    password VARCHAR(255),
    post_no VARCHAR(255),
    real_name VARCHAR(255),
    sex VARCHAR(255),
    user_type VARCHAR(255),
    username VARCHAR(255)
);

COMMENT ON TABLE sys_user IS 'User表';
COMMENT ON COLUMN sys_user.id IS '主键';
COMMENT ON COLUMN sys_user.create_time IS '创建时间';
COMMENT ON COLUMN sys_user.update_time IS '更新时间';
COMMENT ON COLUMN sys_user.create_by IS '创建人';
COMMENT ON COLUMN sys_user.update_by IS '更新人';
COMMENT ON COLUMN sys_user.remark IS '备注';
COMMENT ON COLUMN sys_user.creator IS '创建人姓名';
COMMENT ON COLUMN sys_user.updater IS '更新人姓名';
-- UserRole表 (PostgreSQL) - 注意: Mapper 中使用 sys_user_role
CREATE TABLE sys_user_role (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    data_state VARCHAR(255),
    role_id BIGINT,
    user_id BIGINT
);

COMMENT ON TABLE sys_user_role IS 'UserRole表';
COMMENT ON COLUMN sys_user_role.id IS '主键';
COMMENT ON COLUMN sys_user_role.create_time IS '创建时间';
COMMENT ON COLUMN sys_user_role.update_time IS '更新时间';
COMMENT ON COLUMN sys_user_role.create_by IS '创建人';
COMMENT ON COLUMN sys_user_role.update_by IS '更新人';
COMMENT ON COLUMN sys_user_role.remark IS '备注';
COMMENT ON COLUMN sys_user_role.creator IS '创建人姓名';
COMMENT ON COLUMN sys_user_role.updater IS '更新人姓名';
-- Visit表 (PostgreSQL)
CREATE TABLE visit (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    mobile VARCHAR(255),
    time TIMESTAMP,
    visitor VARCHAR(255),
    type INTEGER,
    status INTEGER DEFAULT 1
);

COMMENT ON TABLE visit IS 'Visit表';
COMMENT ON COLUMN visit.id IS '主键';
COMMENT ON COLUMN visit.create_time IS '创建时间';
COMMENT ON COLUMN visit.update_time IS '更新时间';
COMMENT ON COLUMN visit.create_by IS '创建人';
COMMENT ON COLUMN visit.update_by IS '更新人';
COMMENT ON COLUMN visit.remark IS '备注';
COMMENT ON COLUMN visit.creator IS '创建人姓名';
COMMENT ON COLUMN visit.updater IS '更新人姓名';

-- ============================================================
-- MVP 功能迁移新增表 (2026-08-11)
-- 模块: 物资管理 / 餐饮管理 / 老人档案 / 楼栋管理 / 员工管理
-- ============================================================

-- 员工表 (业务员工档案,区别于 sys_user 系统账号)
CREATE TABLE staff (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    phone VARCHAR(32),
    email VARCHAR(64),
    password VARCHAR(128),
    role_id BIGINT,
    dept_no VARCHAR(32),
    sex VARCHAR(8),
    avatar VARCHAR(512),
    leave_flag VARCHAR(8) DEFAULT '0'
);
COMMENT ON TABLE staff IS '员工表';
COMMENT ON COLUMN staff.leave_flag IS '离职状态 0在职 1离职';

-- 楼栋表
CREATE TABLE building (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    code VARCHAR(32),
    sort_no INTEGER,
    status INTEGER DEFAULT 1
);
COMMENT ON TABLE building IS '楼栋表';

-- 楼层表增加楼栋关联
ALTER TABLE floor ADD COLUMN building_id BIGINT;
COMMENT ON COLUMN floor.building_id IS '楼栋编号';

-- 仓库表
CREATE TABLE warehouse (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    staff_id BIGINT,
    del_flag INTEGER DEFAULT 0
);
COMMENT ON TABLE warehouse IS '仓库表';

-- 物资类别表
CREATE TABLE material_type (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    del_flag INTEGER DEFAULT 0
);
COMMENT ON TABLE material_type IS '物资类别表';

-- 物资表
CREATE TABLE material (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    type_id BIGINT,
    name VARCHAR(64),
    spec VARCHAR(64),
    price NUMERIC(10,2),
    warn_threshold INTEGER,
    del_flag INTEGER DEFAULT 0
);
COMMENT ON TABLE material IS '物资表';
COMMENT ON COLUMN material.warn_threshold IS '库存预警阈值';

-- 入库登记表
CREATE TABLE warehouse_record (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    warehouse_id BIGINT,
    staff_id BIGINT,
    source VARCHAR(64),
    warehouse_time TIMESTAMP,
    status INTEGER DEFAULT 0,
    del_flag INTEGER DEFAULT 0
);
COMMENT ON TABLE warehouse_record IS '入库登记表';
COMMENT ON COLUMN warehouse_record.status IS '入库状态 0待审核 1已通过 2未通过';

-- 入库物资表 (批次库存)
CREATE TABLE warehouse_material (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    warehouse_record_id BIGINT,
    material_id BIGINT,
    warehouse_num INTEGER,
    inventory INTEGER,
    product_date DATE,
    expire_date DATE
);
COMMENT ON TABLE warehouse_material IS '入库物资表(批次库存)';

-- 出库登记表
CREATE TABLE outbound_record (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    warehouse_id BIGINT,
    staff_id BIGINT,
    recipient_id BIGINT,
    recipient_type INTEGER,
    material_use VARCHAR(128),
    outbound_time TIMESTAMP,
    status INTEGER DEFAULT 0,
    del_flag INTEGER DEFAULT 0
);
COMMENT ON TABLE outbound_record IS '出库登记表';
COMMENT ON COLUMN outbound_record.recipient_type IS '领用人类型 0员工 1老人';
COMMENT ON COLUMN outbound_record.status IS '出库状态 0待审核 1已通过 2未通过';

-- 出库物资表
CREATE TABLE outbound_material (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    outbound_record_id BIGINT,
    warehouse_material_id BIGINT,
    material_id BIGINT,
    outbound_num INTEGER
);
COMMENT ON TABLE outbound_material IS '出库物资表';

-- 菜品类别表
CREATE TABLE dishes_type (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    del_flag INTEGER DEFAULT 0
);
COMMENT ON TABLE dishes_type IS '菜品类别表';

-- 菜品表
CREATE TABLE dishes (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    type_id BIGINT,
    name VARCHAR(64),
    price NUMERIC(10,2),
    image VARCHAR(512),
    del_flag INTEGER DEFAULT 0
);
COMMENT ON TABLE dishes IS '菜品表';

-- 餐饮套餐表
CREATE TABLE catering_set (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    name VARCHAR(64),
    month_price NUMERIC(10,2),
    del_flag INTEGER DEFAULT 0
);
COMMENT ON TABLE catering_set IS '餐饮套餐表';

-- 套餐菜品关联表
CREATE TABLE set_dishes (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    set_id BIGINT,
    dishes_id BIGINT
);
COMMENT ON TABLE set_dishes IS '套餐菜品关联表';

-- 订餐表
CREATE TABLE meal_order (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    elder_id BIGINT,
    catering_set_id BIGINT,
    staff_id BIGINT,
    deliver_time TIMESTAMP,
    dine_date DATE,
    dine_type VARCHAR(32),
    pay_amount NUMERIC(10,2),
    status INTEGER DEFAULT 0,
    dine_flag INTEGER DEFAULT 0
);
COMMENT ON TABLE meal_order IS '订餐表';
COMMENT ON COLUMN meal_order.status IS '订单状态 0待支付 1已完成';
COMMENT ON COLUMN meal_order.dine_flag IS '用餐打卡 0未用餐 1已用餐';

-- 订餐菜品表
CREATE TABLE meal_order_dishes (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    meal_order_id BIGINT,
    dishes_id BIGINT,
    dishes_name VARCHAR(64),
    dishes_price NUMERIC(10,2),
    order_num INTEGER,
    set_flag INTEGER DEFAULT 0,
    total_amount NUMERIC(10,2),
    really_amount NUMERIC(10,2)
);
COMMENT ON TABLE meal_order_dishes IS '订餐菜品表';

-- 老人健康数据表 (体检记录)
CREATE TABLE elder_health_data (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    elder_id BIGINT,
    height INTEGER,
    weight NUMERIC(5,2),
    temperature NUMERIC(4,1),
    heart_rate INTEGER,
    systolic_blood_pressure INTEGER,
    diastolic_blood_pressure INTEGER,
    fasting_blood_glucose NUMERIC(5,2),
    postprandial_blood_glucose NUMERIC(5,2),
    blood_oxygen_saturation INTEGER,
    cholesterol NUMERIC(5,2),
    uric_acid INTEGER,
    left_eye NUMERIC(3,1),
    right_eye NUMERIC(3,1),
    left_ear VARCHAR(32),
    right_ear VARCHAR(32),
    muscle_percentage NUMERIC(5,2),
    body_fat_percentage NUMERIC(5,2),
    waist_circumference INTEGER,
    hip_circumference INTEGER,
    moisture_content NUMERIC(5,2),
    check_date TIMESTAMP
);
COMMENT ON TABLE elder_health_data IS '老人健康数据表(体检记录)';

-- 老人健康信息表
CREATE TABLE elder_health_info (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    elder_id BIGINT,
    self_care VARCHAR(64),
    vision VARCHAR(64),
    hearing VARCHAR(64),
    hospital VARCHAR(128),
    doctor VARCHAR(64),
    hospital_phone VARCHAR(32),
    allergy_drug VARCHAR(512),
    medical_history VARCHAR(1024),
    major_disease VARCHAR(512)
);
COMMENT ON TABLE elder_health_info IS '老人健康信息表';

-- 老人生活档案表
CREATE TABLE elder_life_info (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    elder_id BIGINT,
    diet_taboo VARCHAR(512),
    living_habit VARCHAR(512),
    hobby VARCHAR(512),
    religion VARCHAR(64)
);
COMMENT ON TABLE elder_life_info IS '老人生活档案表';

-- 紧急联系人表
CREATE TABLE emergency_contact (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    elder_id BIGINT,
    name VARCHAR(64),
    phone VARCHAR(32),
    email VARCHAR(64),
    relation VARCHAR(32),
    receive_flag VARCHAR(8)
);
COMMENT ON TABLE emergency_contact IS '紧急联系人表';

-- 老人家属表
CREATE TABLE family_member (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    elder_id BIGINT,
    name VARCHAR(64),
    id_num VARCHAR(32),
    phone VARCHAR(32),
    email VARCHAR(64),
    address VARCHAR(255),
    relation VARCHAR(32),
    receive_flag VARCHAR(8),
    del_flag VARCHAR(8) DEFAULT '0'
);
COMMENT ON TABLE family_member IS '老人家属表';

-- 老人档案变更记录表
CREATE TABLE elder_record_log (
    id BIGSERIAL PRIMARY KEY,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    create_by BIGINT,
    update_by BIGINT,
    remark VARCHAR(512),
    creator VARCHAR(64),
    updater VARCHAR(64),
    elder_id BIGINT,
    change_type VARCHAR(32),
    change_content VARCHAR(1024)
);
COMMENT ON TABLE elder_record_log IS '老人档案变更记录表';
