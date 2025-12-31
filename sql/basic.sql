-- 基础模块
-- 用户表
CREATE TABLE `basic_user` (
                              `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `name` VARCHAR(20) NOT NULL COMMENT '名称',
                              `mobile` VARCHAR(20) DEFAULT NULL COMMENT '手机',
                              `password` VARCHAR(50) NOT NULL COMMENT '账号密码',
                              `salt` VARCHAR(16) DEFAULT NULL COMMENT '密码盐值',
                              `email` VARCHAR(20) DEFAULT NULL COMMENT '邮箱',
                              `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像',
                              `signature` VARCHAR(255) DEFAULT NULL COMMENT '个性签名',
                              `state` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '账号状态',
                              `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                              `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识',
                              PRIMARY KEY(`id`),
                              INDEX idx_mobile(mobile)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';


-- 角色表
CREATE TABLE `basic_role` (
                              `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `role_code` VARCHAR(45) NOT NULL COMMENT '角色编号',
                              `role_name` VARCHAR(45) NOT NULL COMMENT '角色名称',
                              `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
                              `state` TINYINT(1) NOT NULL COMMENT '数据状态 0-无效 1-有效',
                              `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                              `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识',
                              PRIMARY KEY(`id`),
                              INDEX idx_role_code(role_code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';


-- 用户-角色表
CREATE TABLE `basic_user_role` (
                                   `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                   `user_id` BIGINT NOT NULL COMMENT '用户id',
                                   `role_id` BIGINT NOT NULL COMMENT '角色id',
                                   `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                                   `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识',
                                   PRIMARY KEY(`id`),
                                   INDEX idx_user_id(user_id),
                                   INDEX idx_role_id(role_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表';


-- 权限表
CREATE TABLE `basic_permission` (
                                    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `permission_code` VARCHAR(45) NOT NULL COMMENT '权限编号',
                                    `permission_name` VARCHAR(100) NOT NULL COMMENT '权限名称',
                                    `permission_type` VARCHAR(45) NOT NULL COMMENT '权限类型',
                                    `href` VARCHAR(255) DEFAULT NULL COMMENT '连接地址',
                                    `icon` VARCHAR(255) DEFAULT NULL COMMENT '图标',
                                    `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
                                    `parent_id` BIGINT(20) NOT NULL COMMENT '父级id',
                                    `depth` INT(11) NOT NULL COMMENT '深度，用来记录树结构的层级关系',
                                    `path` VARCHAR(255) NOT NULL COMMENT '路径，用来记录树结构数据id的路径，用''.''分隔',
                                    `sorting` INT(11) NOT NULL COMMENT '排序字段',
                                    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                                    `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识',
                                    PRIMARY KEY(`id`),
                                    UNIQUE KEY idx_permission_code(permission_code) USING BTREE,
                                    KEY `INX_PARENT_ID` (`parent_id`) USING BTREE,
                                    KEY `INX_PATH` (`path`) USING BTREE,
                                    KEY idx_permission_type(permission_type)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';


-- 角色-权限表
CREATE TABLE `basic_role_permission` (
                                         `id` BIGINT(20) NOT NULL COMMENT '主键',
                                         `role_id` BIGINT(20) NOT NULL COMMENT '角色id',
                                         `permission_id` BIGINT(20) NOT NULL COMMENT '权限id',
                                         `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                                         `remove_flag` TINYINT DEFAULT '0' COMMENT '删除标识',
                                         PRIMARY KEY (`id`),
                                         INDEX idx_role_id(role_id),
                                         INDEX idx_permission_id(permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色权限信息';


-- 公共附件表 basic_attachment
CREATE TABLE basic_attachment (
                                  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `biz_type` VARCHAR(25) NOT NULL COMMENT '关联模块',
                                  `biz_id` BIGINT NOT NULL COMMENT '关联业务id',
                                  `original_name` VARCHAR(50) COMMENT '原始文件名',
                                  `file_url` VARCHAR(512) DEFAULT NULL COMMENT '完整url',
                                  `file_path` VARCHAR(512) DEFAULT NULL COMMENT '存储路径',
                                  `file_type` VARCHAR(50) COMMENT '文件类型',
                                  `file_size` BIGINT DEFAULT 0 COMMENT '文件大小',
                                  `file_ext`VARCHAR(20) COMMENT '文件扩展名',
                                  `user_id` BIGINT NOT NULL COMMENT '创建人id',
                                  `status` TINYINT DEFAULT 1 COMMENT '状态 1-启用 0-禁用',
                                  `message` VARCHAR(100) DEFAULT NULL COMMENT '信息',
                                  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                                  `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识',
                                  PRIMARY KEY(`id`),
                                  INDEX idx_user_id(user_id),
                                  INDEX idx_biz(biz_type, biz_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公共附件表';


-- 用户签到表
CREATE TABLE `basic_sign_in` (
                                 `id` BIGINT(20) NOT NULL COMMENT '主键',
                                 `user_id` BIGINT(20) NOT NULL COMMENT '用户id',
                                 `date` DATE NOT NULL COMMENT '签到日期 yyyy/MM/dd',
                                 `luck` VARCHAR(10) COMMENT '气运',
                                 `background_color` VARCHAR(20) COMMENT '背景色',
                                 `description` VARCHAR(20) COMMENT '气运描述',
                                 `famous_quote_id` BIGINT(20) COMMENT '名句id',
                                 `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                                 `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识',
                                 PRIMARY KEY (`id`),
                                 INDEX idx_user_id(user_id),
                                 INDEX idx_sign_in(user_id, `date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户签到表';


-- 名句表
CREATE TABLE `basic_famous_quote` (
                                      `id` BIGINT(20) NOT NULL COMMENT '主键',
                                      `content` VARCHAR(100) NOT NULL COMMENT '内容',
                                      `author` VARCHAR(20) NOT NULL COMMENT '作者',
                                      `source` VARCHAR(50) NOT NULL COMMENT '出处',
                                      `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                                      `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='名句表';

-- 随机获取一条信息
SELECT * FROM `basic_famous_quote` ORDER BY RAND() LIMIT 1;

CREATE TABLE `dict_region` (
                               `id` bigint(20) NOT NULL COMMENT '主键',
                               `region_code` varchar(50) NOT NULL COMMENT '区域编号',
                               `region_name` varchar(100) NOT NULL COMMENT '区域名称',
                               `region_name_py` varchar(255) DEFAULT NULL COMMENT '区域名称拼音',
                               `short_name` varchar(45) DEFAULT NULL COMMENT '简称',
                               `short_name_py` varchar(100) DEFAULT NULL COMMENT '短拼音',
                               `longitude` decimal(10,6) DEFAULT NULL COMMENT '经度',
                               `latitude` decimal(10,6) DEFAULT NULL COMMENT '纬度',
                               `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
                               `depth` int(11) DEFAULT NULL COMMENT '区域深度，记录字典的层级关系，1/省，2/市，3/区县，4/乡镇',
                               `path` varchar(255) DEFAULT NULL COMMENT '区域路径，用来记录当前类别的id路径，用“.”分隔',
                               `sorting` double(18,2) DEFAULT NULL COMMENT '区域排序',
                               `create_by` bigint(20) NOT NULL COMMENT '创建人',
                               `update_by` bigint(20) NOT NULL COMMENT '最后更新人',
                               `create_time` datetime NOT NULL COMMENT '创建时间',
                               `update_time` datetime NOT NULL COMMENT '最后更新时间',
                               `remove_flag` int(11) NOT NULL COMMENT '逻辑删除标记',
                               PRIMARY KEY (`id`) USING BTREE,
                               KEY `IDX_REGION_CODE` (`region_code`) USING BTREE,
                               KEY `IDX_PARENT_ID` (`parent_id`) USING BTREE,
                               KEY `IDX_REGION_NAME` (`region_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行政区域';