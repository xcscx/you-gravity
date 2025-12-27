package com.itegg.yougravitybackend.model.entity.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.SuperModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 名句 basic_famous_quote
 * @author ITegg
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="basic_famous_quote")
@Data
public class FamousQuote extends SuperModel {

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 作者
     */
    @TableField("author")
    private String author;

    /**
     * 出处
     */
    @TableField("source")
    private String source;

}