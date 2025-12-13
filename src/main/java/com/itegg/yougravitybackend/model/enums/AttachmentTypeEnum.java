package com.itegg.yougravitybackend.model.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

@Getter
public enum AttachmentTypeEnum {

    LOCATION_ATTACHMENT("地点附件", "LOCATION_ATTACHMENT"),
    CHECKIN_ATTACHMENT("打卡附件", "CHECKIN_ATTACHMENT"),
    RECIPE_STEP_ATTACHMENT("菜谱步骤附件", "RECIPE_STEP_ATTACHMENT"),;

    private final String text;

    private final String value;

    /**
     * 初始化
     * @param text 文本
     * @param value 值
     */
    AttachmentTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     */
    public static AttachmentTypeEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (AttachmentTypeEnum anEnum : AttachmentTypeEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

}
