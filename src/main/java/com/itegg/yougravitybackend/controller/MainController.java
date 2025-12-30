package com.itegg.yougravitybackend.controller;

import cn.hutool.core.lang.UUID;
import com.itegg.yougravitybackend.app.NavigationApp;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @Resource
    private NavigationApp navigationApp;

    /**
     * 健康检查
     */
    @GetMapping("/health")
    public Result<String> health() {
        return ResultUtils.ok("ok");
    }

    /**
     * app调用检查
     */
    @GetMapping("/test")
    public void test() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "你好，我是程序员ITegg";
        String answer = navigationApp.doChat(message, chatId);
        System.out.println("answer1 = " + answer);
        // 第二轮
        message = "我想参加上海在劳动节开展的（音律联觉）活动";
        answer = navigationApp.doChat(message, chatId);
        System.out.println("answer2 = " + answer);
        // 第三轮
        message = "我想参加的活动叫什么来着？刚跟你说过，帮我回忆一下";
        answer = navigationApp.doChat(message, chatId);
        System.out.println("answer3 = " + answer);
    }

}
