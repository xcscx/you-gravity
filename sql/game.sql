-- 随机字段表
CREATE TABLE `random_field` (
                                `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
                                `name` VARCHAR(50) NOT NULL COMMENT '名称',
                                `category` VARCHAR(10) NOT NULL COMMENT '分类',
                                `weight` INT DEFAULT 1 COMMENT '权重:越大越靠前',
                                `enable` TINYINT DEFAULT 1 COMMENT '启用标识',
                                `remove_flag` TINYINT DEFAULT 0 COMMENT '删除标识',
                                PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='随机字段表'


-- 补充数据
INSERT INTO `random_field`(`name`, `category`, `weight`) VALUES
-- 时间
('凌晨一点', 'time', '3'),
('凌晨三点', 'time', '2'),
('早上五点', 'time', '2'),
('早上七点', 'time', '2'),
('上午九点', 'time', '2'),
('中午十一点', 'time', '2'),
('下午一点', 'time', '2'),
('下午三点', 'time', '2'),
('下午五点', 'time', '2'),
('晚上七点', 'time', '3'),
('晚上九点', 'time', '3'),
('夜里十一点', 'time', '3'),
-- 地点
('篮球场', 'location', '1'),
('工位', 'location', '1'),
('实验室', 'location', '1'),
('室外', 'location', '1'),
('厕所', 'location', '1'),
('群内', 'location', '1'),
-- 人物
('大gu澳城汤', 'person', '1'),
('大使大斐', 'person', '1'),
('上好佳祺', 'person', '1'),
('瓦特', 'person', '1'),
-- 事件
('偷吃我的袜子', 'event', '1'),
('非法录像', 'event', '1'),
('产生了多余的情感', 'event', '1'),
('骚扰AI', 'event', '1'),
('威胁他人不准进入', 'event', '1'),
('坑队友', 'event', '1')
;




SELECT * FROM random_field
WHERE enable = 1
ORDER BY -LOG((1-RAND())/weight) LIMIT 1;


SELECT
    CONCAT(
        '【', (SELECT `name` FROM random_field WHERE category = 'time' ORDER BY -LOG(1-RAND())/weight LIMIT 1), '】',
        '【', (SELECT `name` FROM random_field WHERE category = 'person' ORDER BY -LOG(1-RAND())/weight LIMIT 1), '】',
        '在',
        '【', (SELECT `name` FROM random_field WHERE category = 'location' ORDER BY -LOG(1-RAND())/weight LIMIT 1), '】',
        '【', (SELECT `name` FROM random_field WHERE category = 'event' ORDER BY -LOG(1-RAND())/weight LIMIT 1), '】'
    ) AS sentence;
