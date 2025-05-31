package com.itegg.yougravitybackend.model.vo;

import com.itegg.yougravitybackend.common.model.IdModel;
import lombok.Data;

@Data
public class AttachmentVO extends IdModel {

    private Long businessId;

    private String businessType;

    private String name;

    private String docUrl;

    private String docType;

}
