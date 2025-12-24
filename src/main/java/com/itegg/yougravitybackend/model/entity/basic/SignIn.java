package com.itegg.yougravitybackend.model.entity.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 用户登录 basic_sign_in
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="basic_sign_in")
@Data
public class SignIn extends SuperModel {

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 签到日期 yyyy/mm/dd
     */
    @TableField("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    /**
     * 气运
     */
    @TableField("luck")
    private String luck;

    /**
     * 背景色
     */
    @TableField("background_color")
    private String backgroundColor;

    /**
     * 气运描述
     */
    @TableField("description")
    private String description;

    /**
     * 名句id
     */
    @TableField("famous_quote_id")
    private Long famousQuoteId;

}