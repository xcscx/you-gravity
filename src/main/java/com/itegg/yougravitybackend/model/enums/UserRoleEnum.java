package com.itegg.yougravitybackend.model.enums;

import lombok.Getter;

/**
 * 用户角色枚举
 * @author: ITegg
 */
@Getter
public enum UserRoleEnum {

    ADMIN("ADMIN", "超级管理员"),

    USER("USER", "用户");

    private String code;

    private String description;

    UserRoleEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static UserRoleEnum getEnumByCode(String code) {
        for (UserRoleEnum value : UserRoleEnum.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

}
