-- 食为天模块
-- 菜谱表 Recipe
CREATE TABLE food_recipe (
                             `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `user_id` BIGINT NOT NULL COMMENT '创建者ID',
                             `title` VARCHAR(25) NOT NULL COMMENT '菜谱名称',
                             `description` VARCHAR(100) DEFAULT NULL COMMENT '描述',
                             `url` VARCHAR(512) DEFAULT NULL COMMENT '封面',
                             `is_public` TINYINT(2) DEFAULT 0 COMMENT '是否公开（0-私有 1-公开）',
                             `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                             `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识',
                             PRIMARY KEY(`id`),
                             INDEX idx_user_id(user_id),
                             INDEX idx_title(title)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜谱表';


-- 菜谱步骤表 RecipeStep
CREATE TABLE food_recipe_step (
                                  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                  `recipe_id` BIGINT NOT NULL COMMENT '菜谱ID',
                                  `step_number` INTEGER(8) NOT NULL COMMENT '步骤顺序（从1开始）',
                                  `description` VARCHAR(100) DEFAULT NULL COMMENT '步骤描述',
                                  `action_type` VARCHAR(100) DEFAULT NULL COMMENT '动作类型动画',
                                  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
                                  `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识',
                                  PRIMARY KEY(`id`),
                                  INDEX idx_recipe_id(recipe_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜谱步骤表';






