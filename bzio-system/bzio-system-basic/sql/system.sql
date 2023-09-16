-- 基于MYSQL数据库

-- 用户表
create table bzio_base.sys_user
(
    user_id                 bigint not null primary key comment '用户id',
    username                varchar(50)  null comment '用户名',
    password                varchar(100) null comment '密码',
    nickname                varchar(100) null comment '用户昵称',
    birthday                timestamp    null comment '生日',
    sex                     tinyint      default 0 comment '性别',
    certificate_type        varchar(50)  null comment '证件类型',
    certificate_num         varchar(50)  null comment '证件号',
    mobile_number           varchar(50)  null comment '手机号码',
    phone_number            varchar(50)  null comment '电话号码',
    email                   varchar(100) null comment '邮箱',
    fax                     varchar(100) null comment '传真',
    avatar                  varchar(10)  null comment '头像',
    status                  tinyint      default 0 comment '状态',
    del_flag                tinyint      default 0 comment '删除状态',
    login_ip                varchar(50)  null comment '最近一次登录ip',
    login_date              timestamp    null comment '最近一次登录日期',
    create_by               varchar(50)  null,
    create_name             varchar(50)  null,
    create_date             timestamp    null,
    update_by               varchar(50)  null,
    update_name             varchar(50)  null,
    update_date             timestamp    null,
    remark                  varchar(50)  null comment '备注'
);

-- 角色表
create table bzio_base.sys_role
(
    role_id             bigint not null primary key,
    role_name           varchar(100) null,
    role_key            varchar(50)  null,
    order_num           int          null,
    menu_check_strictly int          null,
    dept_check_strictly int          null,
    status              tinyint      default 0,
    del_flag            tinyint      default 0,
    create_by           varchar(50)  null,
    create_name         varchar(50)  null,
    create_date         timestamp    null,
    update_by           varchar(50)  null,
    update_name         varchar(50)  null,
    update_date         timestamp    null,
    remark              varchar(50)  null
);

-- 部门表
create table bzio_base.sys_dept
(
    dept_id     bigint not null primary key,
    dept_name   varchar(100) null,
    parent_id   varchar(50)  null,
    parent_name varchar(100) null,
    ancestors   varchar(500) null,
    order_num   int          null,
    leader      varchar(50)  null,
    phone       varchar(50)  null,
    email       varchar(100) null,
    level       varchar(50)  null,
    sub_level   varchar(50)  null,
    status      tinyint      default 0,
    del_flag    tinyint      default 0,
    create_by   varchar(50)  null,
    create_name varchar(50)  null,
    create_date timestamp    null,
    update_by   varchar(50)  null,
    update_name varchar(50)  null,
    update_date timestamp    null,
    remark      varchar(50)  null
);

-- 菜单表
create table bzio_base.sys_menu
(
    menu_id     bigint not null primary key,
    menu_name   varchar(100) null,
    parent_id   varchar(50)  null,
    order_num   int          null,
    path        varchar(50)  null,
    component   varchar(100) null,
    is_frame    tinyint      default 0,
    is_cache    tinyint      default 0,
    menu_type   varchar(50)  null,
    visible     tinyint      default 0,
    status      tinyint      default 0,
    perms       varchar(50)  null,
    icon        varchar(50)  null,
    create_by   varchar(50)  null,
    create_name varchar(50)  null,
    create_date timestamp    null,
    update_by   varchar(50)  null,
    update_name varchar(50)  null,
    update_date timestamp    null,
    remark      varchar(50)  null
);

-- 关联表
create table bzio_base.sys_user_role
(
    id      bigint not null primary key,
    user_id bigint null,
    role_id bigint null
);

create table bzio_base.sys_role_menu
(
    id      bigint not null primary key,
    role_id bigint null,
    menu_id bigint null
);

create table bzio_base.sys_user_dept
(
    id      bigint not null primary key,
    user_id bigint null,
    dept_id bigint null
);

-- 系统日志表
create table bzio_base.sys_log
(
    log_id              bigint not null primary key comment '日志id',
    title               varchar(50)  null comment '日志标题',
    business_type       tinyint      default 0 comment '业务类型',
    log_desc            varchar(50)  null comment '日志描述',
    operator            varchar(50)  null comment '操作人',
    operator_dept       varchar(50)  null comment '操作部门',
    operation_url       varchar(100) null comment '操作请求的url',
    operation_ip        varchar(50)  null comment '操作所在ip地址',
    operation_time      timestamp    null comment '操作时间',
    request_method      varchar(50)  null comment '请求方法',
    request_param       varchar(500) null comment '请求参数',
    response_result     varchar(500) null comment '响应结果',
    error_msg           varchar(100) null comment '错误信息',
    status              tinyint      default 0 comment '状态'
);



-- 系统字典表（类型）
create table bzio_base.sys_dict_type
(
    dict_id     bigint not null primary key,
    dict_name   varchar(50)  null,
    dict_type   varchar(50)  null,
    order_num   tinyint      default 0,
    status      tinyint      default 0,
    note        varchar(500) null,
    create_by   varchar(50)  null,
    create_name varchar(50)  null,
    create_date timestamp    null,
    update_by   varchar(50)  null,
    update_name varchar(50)  null,
    update_date timestamp    null
);

-- 系统字典表（数据）
create table bzio_base.sys_dict_data
(
    dict_code   bigint not null primary key,
    dict_label  varchar(50)  null,
    dict_value  varchar(50)  null,
    dict_type   varchar(50)  null,
    order_num   tinyint      default 0,
    status      tinyint      default 0,
    note        varchar(500) null,
    create_by   varchar(50)  null,
    create_name varchar(50)  null,
    create_date timestamp    null,
    update_by   varchar(50)  null,
    update_name varchar(50)  null,
    update_date timestamp    null
);

-- 系统配置信息表
create table bzio_base.sys_config
(
    config_id       bigint not null primary key,
    config_name     varchar(50)  null,
    config_key      varchar(50)  null,
    config_value    varchar(50)  null,
    note            varchar(500) null,
    create_by       varchar(50)  null,
    create_name     varchar(50)  null,
    create_date     timestamp    null,
    update_by       varchar(50)  null,
    update_name     varchar(50)  null,
    update_date     timestamp    null
);
