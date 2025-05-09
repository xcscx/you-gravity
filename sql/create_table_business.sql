-- 活动表
CREATE TABLE `bus_event` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `event_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动名称',
    `event_city` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动地点',
    `event_start_time` datetime NOT NULL COMMENT '开始时间',
    `event_end_time` datetime NOT NULL COMMENT '结束时间',
    `event_background_image` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '活动背景图片',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `remove_flag` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
    PRIMARY KEY (`id`),
    KEY `idx_eventName` (`event_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='活动表';


-- 活动参与表
create table if not exists bus_event_join_user
(
    id						bigint auto_increment comment 'id' primary key,
    event_id      bigint    not null comment '活动id',
    user_id       bigint    not null comment '用户id',
    create_time  	datetime  default CURRENT_TIMESTAMP  not null comment '创建时间',
    update_time   datetime  default CURRENT_TIMESTAMP  not null comment '最后更新时间',
    remove_flag		TINYINT		default 0		not null comment '是否删除 0-否 1-是',
    INDEX idx_event_user(event_id,user_id)
) comment '活动参与表' collate = utf8mb4_unicode_ci;


-- 地点表
CREATE TABLE `bus_location` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `introduction` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
    `longitude` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '经度',
    `latitude` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '纬度',
    `location_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地点名称',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `remove_flag` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-否 1-是',
    PRIMARY KEY (`id`),
    KEY `idx_locationName` (`location_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地点表';


-- 活动地点关联表
CREATE TABLE `bus_event_location` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `event_id` bigint NOT NULL COMMENT '活动id',
    `location_id` bigint NOT NULL COMMENT '地点id',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `remove_flag` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除0-否 1-是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_event_location` (`event_id`, `location_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='活动地点关联表';


-- 评论表
create table if not exists bus_comment
(
    id						bigint auto_increment comment 'id' primary key,
    event_location_id   bigint          not null comment '地点id',
    message       varchar(128)    null comment '评论信息',
    like_count    int       default 0 null comment '点赞数量',
    parent_id     bigint    default 0 null comment '回复的评论，默认为0',
    create_by      bigint          not null comment '创建人',
    create_time  	datetime  default CURRENT_TIMESTAMP  not null comment '创建时间',
    update_time    datetime  default CURRENT_TIMESTAMP  not null comment '最后更新时间',
    remove_flag		TINYINT		default 0		not null comment '是否删除 0-否 1-是',
    INDEX idx_locationId(location_id)
) comment '评论表' collate = utf8mb4_unicode_ci;


-- 点赞表
create table if not exists bus_comment_like
(
    id						bigint auto_increment comment 'id' primary key,
    user_id        bigint null comment '点赞用户',
    comment_id     bigint null comment '点赞评论',
    create_time    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    INDEX idx_userId(user_id),
    INDEX idx_commentId(comment_id)
    ) comment '点赞表' collate = utf8mb4_unicode_ci;

-- 聚会表




