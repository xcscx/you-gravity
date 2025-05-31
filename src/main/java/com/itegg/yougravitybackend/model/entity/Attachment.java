package com.itegg.yougravitybackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itegg.yougravitybackend.common.model.superModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 附件表
 * @TableName attachment
 */
@TableName(value ="attachment")
@Data
public class Attachment extends superModel {

    @TableField("business_id")
    private Long businessId;

    @TableField("business_type")
    private String businessType;

    @TableField("name")
    private String name;

    @TableField("doc_url")
    private String docUrl;

    @TableField("doc_type")
    private String docType;

    @TableField("create_by")
    private String createBy;

}
