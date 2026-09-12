-- 图库表
CREATE TABLE `gallery` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                           `gallery_name` VARCHAR(128) NOT NULL COMMENT '图库名称',
                           `introduction` VARCHAR(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '介绍',
                           `level_id` BIGINT NOT NULL COMMENT '图库级别id',
                           `used_capacity` INT NOT NULL DEFAULT 0 COMMENT '已用大小，单位 M',
                           `used_image_count` INT NOT NULL DEFAULT 0 COMMENT '已用大小，单位 张',
                           `create_by` BIGINT NOT NULL COMMENT '创建用户 id',
                           `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                           `remove_flag` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除 0-否 1-是',
                           PRIMARY KEY (`id`),
                           KEY `idx_gallery_name` (`gallery_name`),
                           KEY `idx_create_by` (`create_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='图库表';


-- 图片表
CREATE TABLE `gallery_image` (
                                 `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `url` VARCHAR(512) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片url',
                                 `name` VARCHAR(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片名称',
                                 `tags` VARCHAR(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签（JSON数组）',
                                 `pic_size` BIGINT DEFAULT NULL COMMENT '图片体积',
                                 `pic_width` INT DEFAULT NULL COMMENT '图片宽度',
                                 `pic_height` INT DEFAULT NULL COMMENT '图片高度',
                                 `pic_scale` DOUBLE DEFAULT NULL COMMENT '图片宽高比',
                                 `pic_format` VARCHAR(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片格式',
                                 `gallery_id` BIGINT NOT NULL DEFAULT 1 COMMENT '所属图库，公共图库 1',
                                 `create_by` BIGINT NOT NULL COMMENT '创建用户 id',
                                 `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                                 `remove_flag` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除 0-否 1-是',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_name` (`name`),
                                 KEY `idx_gallery_id` (`gallery_id`),
                                 KEY `idx_create_by` (`create_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='图片表';


-- 图库等级表
CREATE TABLE `gallery_level` (
                                 `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `level_name` VARCHAR(128) NOT NULL COMMENT '等级名称',
                                 `introduction` VARCHAR(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '介绍',
                                 `max_capacity` INT NOT NULL DEFAULT 0 COMMENT '最大容量 单位 G',
                                 `max_image_count` INT NOT NULL DEFAULT 0 COMMENT '最大大小 单位 千张',
                                 `create_by` BIGINT NOT NULL COMMENT '创建用户 id',
                                 `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                                 `remove_flag` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除 0-否 1-是',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_level_name` (`level_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='图库等级表';


-- 图库成员表
CREATE TABLE `gallery_member` (
                                  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `gallery_id` BIGINT NOT NULL COMMENT '图库id',
                                  `user_id` BIGINT NOT NULL COMMENT '用户id',
                                  `create_by` BIGINT NOT NULL COMMENT '创建用户 id',
                                  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                                  `remove_flag` TINYINT NOT NULL DEFAULT 0 COMMENT '是否删除 0-否 1-是',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_gallery_user` (`gallery_id`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='图库成员表';

