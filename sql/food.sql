-- 食为天模块
-- 菜谱表 Recipe
CREATE TABLE food_recipe (
                             `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `user_id` BIGINT NOT NULL COMMENT '创建者ID',
                             `title` VARCHAR(25) NOT NULL COMMENT '菜谱名称',
                             `description` VARCHAR(100) DEFAULT NULL COMMENT '描述',
                             `url` VARCHAR(512) DEFAULT NULL COMMENT '封面',
                             `is_public` TINYINT(2) DEFAULT 0 COMMENT '是否公开（0-私有 1-公开）',
                             `status` VARCHAR(25) NOT NULL COMMENT '数据状态 草稿、审核中、审核通过、审核未通过）',
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


-- 食品种类表 IngredientCategory
CREATE TABLE food_ingredient_category (
                                          `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                          `parent_id` BIGINT DEFAULT 0 COMMENT '父分类ID（0表示顶级分类）',
                                          `name` VARCHAR(50) NOT NULL COMMENT '分类名称',
                                          `icon` VARCHAR(255) COMMENT '分类图标',
                                          `description` VARCHAR(200) COMMENT '分类描述',
                                          `sort_order` INT DEFAULT 0 COMMENT '排序序号',
                                          `level` TINYINT DEFAULT 1 COMMENT '分类层级（1-一级，2-二级）',
                                          `status` TINYINT DEFAULT 1 COMMENT '状态：1-启用 0-禁用',
                                          `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                          `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识（0-正常 1-删除）',
                                          PRIMARY KEY(`id`),
                                          INDEX idx_parent_id(parent_id),
                                          INDEX idx_sort(sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='食材分类表';


-- 食材基本信息表 Ingredient
CREATE TABLE food_ingredient (
                                 `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                 `category_id` BIGINT NOT NULL COMMENT '分类ID',
                                 `name` VARCHAR(50) NOT NULL COMMENT '食材名称',
                                 `alias` VARCHAR(200) COMMENT '食材别名（多个用逗号分隔）',
                                 `unit` VARCHAR(20) NOT NULL COMMENT '基础单位（克、毫升、个等）',
--   `calorie` DECIMAL(10,2) COMMENT '每100克/毫升热量（大卡）',
--   `protein` DECIMAL(10,2) COMMENT '蛋白质含量(g/100g)',
--   `fat` DECIMAL(10,2) COMMENT '脂肪含量(g/100g)',
--   `carbohydrate` DECIMAL(10,2) COMMENT '碳水化合物含量(g/100g)',
                                 `image_url` VARCHAR(512) COMMENT '食材图片',
                                 `description` TEXT COMMENT '食材描述',
                                 `storage_tips` VARCHAR(500) COMMENT '存储建议',
                                 `status` TINYINT DEFAULT 1 COMMENT '状态：1-启用 0-禁用',
                                 `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识（0-正常 1-删除）',
                                 PRIMARY KEY(`id`),
                                 UNIQUE KEY uk_name(name),
                                 INDEX idx_category_id(category_id),
                                 FULLTEXT INDEX idx_name_alias(name, alias) COMMENT '全文索引，支持别名搜索'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='食材基本信息表';


-- 菜谱食材关联表 RecipeIngredient
CREATE TABLE food_recipe_ingredient (
                                        `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                        `recipe_id` BIGINT NOT NULL COMMENT '菜谱id',
                                        `ingredient_id` BIGINT NOT NULL COMMENT '食材id',
                                        `ingredient_name` VARCHAR(50) NOT NULL COMMENT '食材名称',
                                        `category_id` BIGINT COMMENT '分类id',
                                        `category_name` VARCHAR(50) COMMENT '分类名称',
                                        `amount` DECIMAL(10,3) NOT NULL COMMENT '用量数值',
                                        `unit` VARCHAR(20) NOT NULL COMMENT '用量单位（克、毫升、个、适量、少许等）',
                                        `remark` VARCHAR(100) COMMENT '备注（如：切块、切片、去核等）',
                                        `preparation` VARCHAR(100) COMMENT '预处理说明（如：洗净、泡发、腌制）',
                                        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                        `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识（0-正常 1-删除）',
                                        PRIMARY KEY(`id`),
                                        INDEX idx_recipe_id(recipe_id),
                                        INDEX idx_ingredient_id(ingredient_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜谱食材关联表';