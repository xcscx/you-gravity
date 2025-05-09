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
    `create_by` bigint NOT NULL COMMENT '创建人id',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `remove_flag` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tagName` (`tag_name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户标签表';


-- 图片表
CREATE TABLE IF NOT EXISTS `picture` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `url` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片url',
    `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片名称',
    `introduction` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
    `category` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分类',
    `tags` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签（JSON数组）',
    `pic_size` bigint DEFAULT NULL COMMENT '图片体积',
    `pic_width` int DEFAULT NULL COMMENT '图片宽度',
    `pic_height` int DEFAULT NULL COMMENT '图片高度',
    `pic_scale` double DEFAULT NULL COMMENT '图片宽高比',
    `pic_format` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片格式',
    `space_id` bigint NOT NULL COMMENT '空间id',
    `create_by` bigint NOT NULL COMMENT '创建用户 id',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remove_flag` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
    `review_status` int NOT NULL DEFAULT '0' COMMENT '审核状态: 0-待审核 1-通过 2-拒绝',
    `review_message` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核信息',
    `reviewer_id` bigint DEFAULT NULL COMMENT '审核人 ID',
    `review_time` datetime DEFAULT NULL COMMENT '审核时间',
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`),
    KEY `idx_introduction` (`introduction`),
    KEY `idx_category` (`category`),
    KEY `idx_tags` (`tags`),
    KEY `idx_userId` (`create_by`),
    KEY `idx_reviewStatus` (`review_status`),
    KEY `idx_spaceId` (`space_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1888056294691676163 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='图片表';


-- 图库空间表
create table if not exists image_space
(
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `space_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '空间名称',
    `space_desc` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '空间描述',
    `space_level` int default 0 COMMENT '空间等级 0-普通 1-专业 2-旗舰',
    `max_size` bigint default 0 COMMENT '最大容量',
    `max_count` bigint default 0 COMMENT '最大数量',
    `total_size` bigint default 0 COMMENT '已使用容量',
    `total_count` bigint default 0 COMMENT '已使用数量',
    `create_by` bigint NOT NULL COMMENT '创建人id',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `remove_flag` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
    PRIMARY KEY (`id`),
    KEY `idx_spaceName` (`space_name`),
    KEY `idx_spaceLevel` (`space_level`),
    KEY `idx_createBy` (`create_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='图库空间表';



