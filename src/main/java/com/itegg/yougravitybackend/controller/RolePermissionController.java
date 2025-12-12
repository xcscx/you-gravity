package com.itegg.yougravitybackend.controller;

import com.itegg.yougravitybackend.service.basic.RolePermissionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色权限 Controller层
 * @author ITegg
 */
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {

    @Resource
    private RolePermissionService rolePermissionService;

}
