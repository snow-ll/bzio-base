-- 基于MYSQL数据库

-- 用户表
create table bzio_base.sys_user
(
    user_id      varchar(50)  not null primary key ,
    username     varchar(50)  null,
    password     varchar(100) null,
    nickname     varchar(100) null,
    birthday     timestamp    null,
    sex          tinyint      null,
    id_card      varchar(50)  null,
    phone_number varchar(50)  null,
    email        varchar(100) null,
    fax          varchar(100) null,
    avatar       varchar(10)  null,
    status       tinyint      null,
    del_flag     tinyint      null,
    login_ip     varchar(50)  null,
    login_date   timestamp    null,
    create_by    varchar(50)  null,
    create_name  varchar(50)  null,
    create_date  timestamp    null,
    update_by    varchar(50)  null,
    update_name  varchar(50)  null,
    update_date  timestamp    null,
    remark       varchar(50)  null
);

-- 角色表
create table bzio_base.sys_role
(
    role_id             varchar(50)  not null primary key,
    role_name           varchar(100) null,
    role_key            varchar(50)  null,
    role_sort           int          null,
    menu_check_strictly int          null,
    dept_check_strictly int          null,
    status              tinyint      null,
    del_flag            tinyint      null,
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
    dept_id     varchar(50)  not null primary key,
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
    status      tinyint      null,
    del_flag    tinyint      null,
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
    menu_id     varchar(50)  not null primary key,
    menu_name   varchar(100) null,
    parent_id   varchar(50)  null,
    order_num   int          null,
    path        varchar(50)  null,
    component   varchar(100) null,
    is_frame    tinyint      null,
    is_cache    tinyint      null,
    menu_type   varchar(50)  null,
    visible     tinyint      null,
    status      tinyint      null,
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
    id      varchar(50) not null primary key,
    user_id varchar(50) null,
    role_id varchar(50) null
);

create table bzio_base.sys_role_menu
(
    id      varchar(50) not null primary key,
    role_id varchar(50) null,
    menu_id varchar(50) null
);

create table bzio_base.sys_user_dept
(
    id      varchar(50) not null primary key,
    user_id varchar(50) null,
    dept_id varchar(50) null
);

-- 系统日志表
create table bzio_base.sys_log
(
    log_id              varchar(50)  not null primary key,
    title               varchar(50)  null,
    business_type       tinyint      null,
    log_desc            varchar(50)  null,
    operator            varchar(50)  null,
    operator_dept       varchar(50)  null,
    operation_url       varchar(100) null,
    operation_ip        varchar(50)  null,
    operation_time      timestamp    null,
    request_method      varchar(50)  null,
    request_param       varchar(500) null,
    response_result     varchar(500) null,
    error_msg           varchar(100) null,
    status              tinyint      null
);

-- 系统字典表（类型）
create table bzio_base.sys_dict_type
(
    dict_id     varchar(50)  not null primary key,
    dict_name   varchar(50)  null,
    dict_type   varchar(50)  null,
    status      tinyint      null,
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
    dict_code   varchar(50)  not null primary key,
    dict_sort   varchar(50)  null,
    dict_label  varchar(50)  null,
    dict_value  varchar(50)  null,
    dict_type   varchar(50)  null,
    status      tinyint      null,
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
    config_id       varchar(50)  not null primary key,
    config_name     varchar(50)  null,
    config_key      varchar(50)  null,
    config_value    varchar(50)  null,
    create_by       varchar(50)  null,
    create_name     varchar(50)  null,
    create_date     timestamp    null,
    update_by       varchar(50)  null,
    update_name     varchar(50)  null,
    update_date     timestamp    null
);
