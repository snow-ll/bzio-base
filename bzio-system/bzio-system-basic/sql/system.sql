-- 基于MYSQL数据库

-- 用户表
create table bzio_base.sys_user
(
    user_id      varchar(50)  not null primary key ,
    user_name    varchar(50)  null,
    password     varchar(100) null,
    nick_name    varchar(100) null,
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












-- 基于DM数据库

-- 用户表
CREATE TABLE "BZIO-BASE"."SYS_USER"
(
    "USER_ID" VARCHAR2(50) NOT NULL,
    "USER_NAME" VARCHAR2(50),
    "PASSWORD" VARCHAR2(100),
    "NICK_NAME" VARCHAR2(100),
    "BIRTHDAY" DATETIME(6),
    "SEX" BIT,
    "ID_CARD" VARCHAR2(50),
    "PHONE_NUMBER" VARCHAR2(50),
    "EMAIL" VARCHAR2(100),
    "FAX" VARCHAR2(100),
    "AVATAR" VARCHAR2(10),
    "STATUS" CHAR(1),
    "DEL_FLAG" BIT,
    "LOGIN_IP" VARCHAR2(50),
    "LOGIN_DATE" DATETIME(6),
    "CREATE_BY" VARCHAR2(50),
    "CREATE_NAME" VARCHAR2(50),
    "CREATE_DATE" DATETIME(6),
    "UPDATE_BY" VARCHAR2(50),
    "UPDATE_NAME" VARCHAR2(50),
    "UPDATE_DATE" DATETIME(6),
    "REMARK" VARCHAR2(50),
    CLUSTER PRIMARY KEY("USER_ID")) STORAGE(ON "MAIN", CLUSTERBTR) ;
COMMENT ON TABLE "BZIO-BASE"."SYS_USER" IS '用户表';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."USER_ID" IS '用户ID';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."USER_NAME" IS '用户名';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."PASSWORD" IS '密码';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."NICKNAME" IS '用户昵称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."BIRTHDAY" IS '出生日期';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."SEX" IS '用户性别（0男 1女）';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."ID_CARD" IS '身份证号';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."PHONE_NUMBER" IS '手机号码';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."EMAIL" IS '用户邮箱';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."FAX" IS '传真';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."AVATAR" IS '头像路径';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."STATUS" IS '帐号状态';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."DEL_FLAG" IS '删除情况';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."LOGIN_IP" IS '最后登录IP';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."LOGIN_DATE" IS '最后登录时间';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."CREATE_NAME" IS '创建者昵称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."CREATE_DATE" IS '创建时间';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."UPDATE_NAME" IS '更新者昵称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."UPDATE_DATE" IS '更新时间';
COMMENT ON COLUMN "BZIO-BASE"."SYS_USER"."REMARK" IS '备注';

-- 角色表
CREATE TABLE "BZIO-BASE"."SYS_ROLE"
(
    "ROLE_ID" VARCHAR2(50) NOT NULL,
    "ROLE_NAME" VARCHAR2(100),
    "ROLE_KEY" VARCHAR2(50),
    "ROLE_SORT" INTEGER,
    "MENU_CHECK_STRICTLY" INTEGER,
    "DEPT_CHECK_STRICTLY" INTEGER,
    "STATUS" CHAR(1),
    "DEL_FLAG" BIT,
    "CREATE_BY" VARCHAR2(50),
    "CREATE_NAME" VARCHAR2(50),
    "CREATE_DATE" DATETIME(6),
    "UPDATE_BY" VARCHAR2(50),
    "UPDATE_NAME" VARCHAR2(50),
    "UPDATE_DATE" DATETIME(6),
    "REMARK" VARCHAR2(50),
    CLUSTER PRIMARY KEY("ROLE_ID")) STORAGE(ON "MAIN", CLUSTERBTR) ;
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."ROLE_ID" IS '角色主键';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."ROLE_NAME" IS '角色名称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."ROLE_KEY" IS '角色权限字符串';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."ROLE_SORT" IS '显示顺序';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."MENU_CHECK_STRICTLY" IS '菜单树选择项是否关联显示';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."DEPT_CHECK_STRICTLY" IS '部门树选择项是否关联显示';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."STATUS" IS '角色状态（0正常 1停用）';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."DEL_FLAG" IS '删除标志（0代表存在 2代表删除）';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."CREATE_NAME" IS '创建者昵称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."CREATE_DATE" IS '创建时间';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."UPDATE_NAME" IS '更新者昵称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."UPDATE_DATE" IS '更新时间';
COMMENT ON COLUMN "BZIO-BASE"."SYS_ROLE"."REMARK" IS '备注';

-- 部门表
CREATE TABLE "BZIO-BASE"."SYS_DEPT"
(
    "DEPT_ID" VARCHAR2(50) NOT NULL,
    "DEPT_NAME" VARCHAR2(100),
    "PARENT_ID" VARCHAR2(50),
    "PARENT_NAME" VARCHAR2(100),
    "ANCESTORS" VARCHAR2(500),
    "ORDER_NUM" INTEGER,
    "LEADER" VARCHAR2(50),
    "PHONE" VARCHAR2(50),
    "EMAIL" VARCHAR2(100),
    "LEVEL" VARCHAR2(50),
    "SUB_LEVEL" VARCHAR2(50),
    "STATUS" CHAR(1),
    "DEL_FLAG" BIT,
    "CREATE_BY" VARCHAR2(50),
    "CREATE_NAME" VARCHAR2(50),
    "CREATE_DATE" DATETIME(6),
    "UPDATE_BY" VARCHAR2(50),
    "UPDATE_NAME" VARCHAR2(50),
    "UPDATE_DATE" DATETIME(6),
    "REMARK" VARCHAR2(50),
    CLUSTER PRIMARY KEY("DEPT_ID")) STORAGE(ON "MAIN", CLUSTERBTR) ;
COMMENT ON TABLE "BZIO-BASE"."SYS_DEPT" IS '部门表';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."DEPT_ID" IS '部门ID';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."DEPT_NAME" IS '部门名称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."PARENT_ID" IS '父部门ID';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."PARENT_NAME" IS '父部门名称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."ANCESTORS" IS '祖级列表';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."ORDER_NUM" IS '显示顺序';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."LEADER" IS '负责人';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."PHONE" IS '部门联系电话';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."EMAIL" IS '部门邮箱';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."LEVEL" IS '组织机构层级编码';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."SUB_LEVEL" IS '组织机构子层级编码';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."STATUS" IS '部门状态:0正常,1停用';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."DEL_FLAG" IS '删除标志（0代表存在 1代表删除）';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."CREATE_NAME" IS '创建者昵称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."CREATE_DATE" IS '创建时间';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."UPDATE_NAME" IS '更新者昵称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."UPDATE_DATE" IS '更新时间';
COMMENT ON COLUMN "BZIO-BASE"."SYS_DEPT"."REMARK" IS '备注';

-- 菜单表
CREATE TABLE "BZIO-BASE"."SYS_MENU"
(
    "MENU_ID" VARCHAR2(50) NOT NULL,
    "MENU_NAME" VARCHAR2(100),
    "PARENT_ID" VARCHAR2(50),
    "ORDER_NUM" INTEGER,
    "PATH" VARCHAR2(50),
    "COMPONENT" VARCHAR2(100),
    "IS_FRAME" CHAR(1),
    "IS_CACHE" CHAR(1),
    "MENU_TYPE" VARCHAR2(50),
    "VISIBLE" CHAR(1),
    "STATUS" CHAR(1),
    "PERMS" VARCHAR2(50),
    "ICON" VARCHAR2(50),
    "CREATE_BY" VARCHAR2(50),
    "CREATE_NAME" VARCHAR2(50),
    "CREATE_DATE" DATETIME(6),
    "UPDATE_BY" VARCHAR2(50),
    "UPDATE_NAME" VARCHAR2(50),
    "UPDATE_DATE" DATETIME(6),
    "REMARK" VARCHAR2(50),
    CLUSTER PRIMARY KEY("MENU_ID")) STORAGE(ON "MAIN", CLUSTERBTR) ;
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."MENU_ID" IS '菜单主键';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."MENU_NAME" IS '菜单名称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."PARENT_ID" IS '父菜单ID';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."ORDER_NUM" IS '显示顺序';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."PATH" IS '请求地址';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."COMPONENT" IS '路由地址';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."IS_FRAME" IS '是否为外链（0是 1否）';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."IS_CACHE" IS '是否缓存（0缓存 1不缓存）';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."MENU_TYPE" IS '菜单类型（M目录 C菜单 F按钮）';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."VISIBLE" IS '菜单状态（0显示 1隐藏）';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."STATUS" IS '菜单状态（0正常 1停用）';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."PERMS" IS '权限标识';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."ICON" IS '菜单图标';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."CREATE_BY" IS '创建者';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."CREATE_NAME" IS '创建者昵称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."CREATE_DATE" IS '创建时间';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."UPDATE_BY" IS '更新者';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."UPDATE_NAME" IS '更新者昵称';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."UPDATE_DATE" IS '更新时间';
COMMENT ON COLUMN "BZIO-BASE"."SYS_MENU"."REMARK" IS '备注';

-- 关联表
CREATE TABLE "BZIO-BASE"."SYS_USER_ROLE"
(
    "ID" VARCHAR2(50) NOT NULL,
    "USER_ID" VARCHAR2(50),
    "ROLE_ID" VARCHAR2(50),
    CLUSTER PRIMARY KEY("ID")) STORAGE(ON "MAIN", CLUSTERBTR) ;

CREATE TABLE "BZIO-BASE"."SYS_USER_DEPT"
(
    "ID" VARCHAR2(50) NOT NULL,
    "USER_ID" VARCHAR2(50),
    "DEPT_ID" VARCHAR2(50),
    CLUSTER PRIMARY KEY("ID")) STORAGE(ON "MAIN", CLUSTERBTR) ;

CREATE TABLE "BZIO-BASE"."SYS_ROLE_MENU"
(
    "ID" VARCHAR2(50) NOT NULL,
    "ROLE_ID" VARCHAR2(50),
    "MENU_ID" VARCHAR2(50),
    CLUSTER PRIMARY KEY("ID")) STORAGE(ON "MAIN", CLUSTERBTR) ;