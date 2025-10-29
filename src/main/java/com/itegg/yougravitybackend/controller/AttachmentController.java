package com.itegg.yougravitybackend.controller;

import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import com.itegg.yougravitybackend.model.entity.Attachment;
import com.itegg.yougravitybackend.service.AttachmentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 附件 控制器
 * @author ITegg
 */
@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Resource
    private AttachmentService attachmentService;

    /**
     * 上传附件
     */
    @PostMapping("/upload")
    public Result<Attachment> uploadAttachment() {
        return ResultUtils.ok(new Attachment());
    }

    /**
     * 删除附件
     */
    @PostMapping("/delete")
    public Result<Boolean> deleteAttachment() {
        return ResultUtils.ok(true);
    }

}
