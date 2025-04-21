-- 活动表
create table if not exists bus_event
(
    id						bigint auto_increment comment 'id' primary key,
    event_name    varchar(128)    not null comment '活动名称',
    event_city    varchar(128)    not null comment '活动地点',
    event_start_time  datetime    not null comment '开始时间',
    event_end_time  datetime      not null comment '结束时间',
    event_background_image  varchar(512) null comment '活动背景图片',
    create_time  	datetime  default CURRENT_TIMESTAMP  not null comment '创建时间',
    update_time   datetime  default CURRENT_TIMESTAMP  not null comment '最后更新时间',
    remove_flag		TINYINT		default 0		not null comment '是否删除 0-否 1-是',
    UNIQUE KEY uk_eventName(event_name)
    ) comment '活动表' collate = utf8mb4_unicode_ci;


-- 地点表
create table if not exists bus_location
(
    id						bigint auto_increment comment 'id' primary key,
    event_id      bigint          not null comment '活动id',
    longitude     varchar(128)    not null comment '经度',
    latitude      varchar(128)    not null comment '纬度',
    location_name  varchar(128)    null comment '地点名称',
    create_time  	datetime  default CURRENT_TIMESTAMP  not null comment '创建时间',
    update_time   datetime  default CURRENT_TIMESTAMP  not null comment '最后更新时间',
    remove_flag		TINYINT		default 0		not null comment '是否删除 0-否 1-是',
    INDEX idx_locationName(location_name)
    ) comment '地点表' collate = utf8mb4_unicode_ci;


-- 评论表
create table if not exists bus_comment
(
    id						bigint auto_increment comment 'id' primary key,
    location_id   bigint          not null comment '地点id',
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






