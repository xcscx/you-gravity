package com.itegg.yougravitybackend.controller;

import com.itegg.yougravitybackend.service.basic.RoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色 Controller层
 * @author ITegg
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;



}
