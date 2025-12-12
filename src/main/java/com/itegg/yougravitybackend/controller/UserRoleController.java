package com.itegg.yougravitybackend.controller;

import com.itegg.yougravitybackend.service.basic.UserRoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色 Controller层
 * @author ITegg
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;


}
