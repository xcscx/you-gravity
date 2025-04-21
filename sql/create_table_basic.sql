-- 用户表
create table if not exists user
(
    id						bigint auto_increment comment 'id' primary key,
    user_account		varchar(256)			not null comment '用户账号',
    user_password	varchar(512)			not null comment '密文密码',
    user_name			varchar(256)			null comment '用户昵称',
    user_avatar		varchar(1024)			null comment '用户头像',
    user_profile  	varchar(512)			null comment '用户简介',
    tags					varchar(512)			null comment '用户标签',
    user_role 			varchar(256) default 'user' not null comment '用户角色 user/admin',
    create_time  	datetime  default CURRENT_TIMESTAMP  not null comment '创建时间',
    update_time    datetime  default CURRENT_TIMESTAMP  not null comment '最后更新时间',
    remove_flag		TINYINT		default 0		not null comment '是否删除 0-否 1-是',
    UNIQUE KEY uk_userAccount(user_account),
    INDEX idx_userName(user_name)
    ) comment '用户表' collate = utf8mb4_unicode_ci;


-- 标签表
create table if not exists user_tag
(
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `tag_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签名',
    `parent_id` bigint DEFAULT '0' COMMENT '父标签id',
    `is_parent` tinyint DEFAULT '0' COMMENT '是否为父标签:0不是-1是',
    `create_by` bigint NOT NULL COMMENT '创建人名称',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `remove_flag` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tagName` (`tag_name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户标签表';







