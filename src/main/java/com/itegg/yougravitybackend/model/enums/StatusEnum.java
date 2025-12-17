package com.itegg.yougravitybackend.model.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

import java.util.Objects;

/**
 * 审核状态枚举
 * @author ITegg
 */
@Getter
public enum StatusEnum {

    DREFT("草稿", "DREFT"),

    REVIEWING("审核中", "REVIEWING"),

    APPROVE("批准", "Approve"),

    REFUSE("拒绝",  "REFUSE");

    private final String text;

    private final String value;

    StatusEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     */
    public static StatusEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (StatusEnum anEnum : StatusEnum.values()) {
            if (Objects.equals(anEnum.value, value)) {
                return anEnum;
            }
        }
        return null;
    }

}
