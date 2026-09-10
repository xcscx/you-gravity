package com.itegg.yougravitybackend.model.vo.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户脱敏VO对象类
 * @author ITegg
 */
@Data
public class UserDesVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

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
     * 用户头像
     */
    private String avatar;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 账号状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}

