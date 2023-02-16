-- 基于MYSQL数据库

-- 用户表
create table bzio_base.sys_user
(
    USER_ID      varchar(50)  not null primary key ,
    USER_NAME    varchar(50)  null,
    PASSWORD     varchar(100) null,
    NICK_NAME    varchar(100) null,
    BIRTHDAY     timestamp    null,
    SEX          tinyint      null,
    ID_CARD      varchar(50)  null,
    PHONE_NUMBER varchar(50)  null,
    EMAIL        varchar(100) null,
    FAX          varchar(100) null,
    AVATAR       varchar(10)  null,
    STATUS       char         null,
    DEL_FLAG     tinyint      null,
    LOGIN_IP     varchar(50)  null,
    LOGIN_DATE   timestamp    null,
    CREATE_BY    varchar(50)  null,
    CREATE_NAME  varchar(50)  null,
    CREATE_DATE  timestamp    null,
    UPDATE_BY    varchar(50)  null,
    UPDATE_NAME  varchar(50)  null,
    UPDATE_DATE  timestamp    null,
    REMARK       varchar(50)  null
);

-- 角色表
create table bzio_base.sys_role
(
    ROLE_ID             varchar(50)  not null primary key,
    ROLE_NAME           varchar(100) null,
    ROLE_KEY            varchar(50)  null,
    ROLE_SORT           int          null,
    MENU_CHECK_STRICTLY int          null,
    DEPT_CHECK_STRICTLY int          null,
    STATUS              char         null,
    DEL_FLAG            tinyint      null,
    CREATE_BY           varchar(50)  null,
    CREATE_NAME         varchar(50)  null,
    CREATE_DATE         timestamp    null,
    UPDATE_BY           varchar(50)  null,
    UPDATE_NAME         varchar(50)  null,
    UPDATE_DATE         timestamp    null,
    REMARK              varchar(50)  null
);

-- 部门表
create table bzio_base.sys_dept
(
    DEPT_ID     varchar(50)  not null
        primary key,
    DEPT_NAME   varchar(100) null,
    PARENT_ID   varchar(50)  null,
    PARENT_NAME varchar(100) null,
    ANCESTORS   varchar(500) null,
    ORDER_NUM   int          null,
    LEADER      varchar(50)  null,
    PHONE       varchar(50)  null,
    EMAIL       varchar(100) null,
    LEVEL       varchar(50)  null,
    SUB_LEVEL   varchar(50)  null,
    STATUS      char         null,
    DEL_FLAG    tinyint      null,
    CREATE_BY   varchar(50)  null,
    CREATE_NAME varchar(50)  null,
    CREATE_DATE timestamp    null,
    UPDATE_BY   varchar(50)  null,
    UPDATE_NAME varchar(50)  null,
    UPDATE_DATE timestamp    null,
    REMARK      varchar(50)  null
);

-- 菜单表
create table bzio_base.sys_menu
(
    MENU_ID     varchar(50)  not null
        primary key,
    MENU_NAME   varchar(100) null,
    PARENT_ID   varchar(50)  null,
    ORDER_NUM   int          null,
    PATH        varchar(50)  null,
    COMPONENT   varchar(100) null,
    IS_FRAME    char         null,
    IS_CACHE    char         null,
    MENU_TYPE   varchar(50)  null,
    VISIBLE     char         null,
    STATUS      char         null,
    PERMS       varchar(50)  null,
    ICON        varchar(50)  null,
    CREATE_BY   varchar(50)  null,
    CREATE_NAME varchar(50)  null,
    CREATE_DATE timestamp    null,
    UPDATE_BY   varchar(50)  null,
    UPDATE_NAME varchar(50)  null,
    UPDATE_DATE timestamp    null,
    REMARK      varchar(50)  null
);

-- 关联表
create table bzio_base.sys_user_role
(
    ID      varchar(50) not null primary key,
    USER_ID varchar(50) null,
    ROLE_ID varchar(50) null
);

create table bzio_base.sys_role_menu
(
    ID      varchar(50) not null primary key,
    ROLE_ID varchar(50) null,
    MENU_ID varchar(50) null
);

create table bzio_base.sys_user_dept
(
    ID      varchar(50) not null primary key,
    USER_ID varchar(50) null,
    DEPT_ID varchar(50) null
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