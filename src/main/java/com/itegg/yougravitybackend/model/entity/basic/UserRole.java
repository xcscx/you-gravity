package com.itegg.yougravitybackend.model.entity.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色 basic_user_role
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="basic_user_role")
@Data
public class UserRole extends superModel {

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

}
