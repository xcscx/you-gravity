package com.itegg.yougravitybackend.controller;

import com.itegg.yougravitybackend.service.basic.PermissionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限 Controller层
 * @author ITegg
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;





}
