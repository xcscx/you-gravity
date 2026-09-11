package com.itegg.yougravitybackend.model.vo.user;

import com.itegg.yougravitybackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户查询类
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色
     */
    private String userRole;

    /**
     * 账号状态
     */
    private Integer state;

}
