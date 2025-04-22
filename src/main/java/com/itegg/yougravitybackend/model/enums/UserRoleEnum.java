package com.itegg.yougravitybackend.model.enums;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

@Getter
public enum UserRoleEnum {

    USER("user", "用户"),

    ADMIN("admin", "管理员");

    private final String code;

    private final String description;

    UserRoleEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static UserRoleEnum getEnumByCode(String code) {
        if(ObjectUtil.isEmpty(code)) {
            return null;
        }
        for(UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if(userRoleEnum.code.equals(code)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}

