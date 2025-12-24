package com.itegg.yougravitybackend.model.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

/**
 * 状态枚举
 * @author ITegg
 */
@Getter
public enum StateEnum {

    VALID("有效", 0),
    INVALID("无效", 1);

    private final String text;

    private final int value;

    StateEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     */
    public static StateEnum getEnumByValue(Integer value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (StateEnum anEnum : StateEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }


}
