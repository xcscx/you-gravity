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
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表'


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
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表'


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
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表'


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
                                    INDEX idx_permission_code(permission_code) USING BTREE,
                                    KEY `INX_PARENT_ID` (`parent_id`) USING BTREE,
                                    KEY `INX_PATH` (`path`) USING BTREE,
                                    INDEX idx_permission_type(permission_type)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表'


-- 角色-权限表
CREATE TABLE `basic_role_permission` (
                                          `id` BIGINT(20) NOT NULL COMMENT '主键',
                                          `role_id` BIGINT(20) NOT NULL COMMENT '角色id',
                                          `permission_id` BIGINT(20) NOT NULL COMMENT '权限id',
                                          `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                          `update_time` DATETIME NOT NULL COMMENT '最后更新时间',
                                          `remove_flag` TINYINT(1) NOT NULL COMMENT '逻辑删除标记，0 - 未删除，1 - 已删除',
                                          PRIMARY KEY (`id`),
                                          INDEX idx_role_id(role_id),
                                          INDEX idx_permission_id(permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色权限信息';


