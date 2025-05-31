package com.itegg.yougravitybackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.AttachmentMapper;
import com.itegg.yougravitybackend.model.entity.Attachment;
import com.itegg.yougravitybackend.service.AttachmentService;
import org.springframework.stereotype.Service;

/**
 * @author itegg
 * @description 针对表【attachment】的数据库操作Service实现
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {


}
