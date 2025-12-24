package com.itegg.yougravitybackend.model.enums;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 每日幸运状态枚举
 * @author ITegg
 */
@Getter
public enum LuckEnum {

    GREAT_LUCKY("大吉", 10.0, "#FF6B6B", "大吉大利，万事如意"),
    LUCKY("吉", 30.0, "#4CAF50","吉星高照，心想事成"),
    LITTLE_LUCKY("末吉", 20.0, "#2196F3","稍有小成，继续努力"),
    NORMAL("平", 20.0, "#9E9E9E","平平淡淡，安稳度日"),
    UNLUCKY("凶", 15.0, "#FF9800", "稍有波折，多加注意"),
    GREAT_UNLUCKY("大凶", 5.0, "#757575", "困难重重，谨慎行事");

    /**
     * 类型名称
     */
    private final String name;

    /**
     * 概率百分比
     */
    private final double probability;

    /**
     * 颜色
     */
    private final String color;

    /**
     * 描述
     */
    private final String description;

    /**
     * 初始化
     */
    LuckEnum(String name, double probability, String color, String description) {
        this.name = name;
        this.probability = probability;
        this.color = color;
        this.description = description;
    }

    /**
     * 获取每日运势
     * @param userId 用户ID
     * @return 运势信息
     */
    public static LuckEnum getDailyFortune(Long userId) {
        LocalDate today = LocalDate.now();
        return getDailyFortune(userId, today);
    }

    public static LuckEnum getDailyFortune(Long userId, LocalDate date) {
        // 1. 生成唯一字符串：用户ID + 日期
        String uniqueKey = userId + ":" + date.format(DateTimeFormatter.ISO_DATE);

        // 2. 计算Hash值
        int hash = Math.abs(uniqueKey.hashCode());

        // 3. 映射到0-99的范围
        int value = hash % 100;

        // 4. 根据概率范围分配
        if (value < 10) {
            // 0-9: 大吉 10%
            return LuckEnum.GREAT_LUCKY;
        } else if (value < 40) {
            // 10-39: 吉 30%
            return LuckEnum.LUCKY;
        } else if (value < 60) {
            // 40-59: 末吉 20%
            return LuckEnum.LITTLE_LUCKY;
        } else if (value < 80) {
            // 60-79: 平 20%
            return LuckEnum.NORMAL;
        } else if (value < 95) {
            // 80-94: 凶 15%
            return LuckEnum.UNLUCKY;
        } else {
            // 95-99: 大凶 5%
            return LuckEnum.GREAT_UNLUCKY;
        }
    }

}
