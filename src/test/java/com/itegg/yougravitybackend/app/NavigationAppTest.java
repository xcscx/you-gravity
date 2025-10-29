package com.itegg.yougravitybackend.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
@SpringBootTest
class NavigationAppTest {

    @Resource
    private NavigationApp loveApp;

    @Test
    void testChat() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "你好，我是程序员ATDawn";
        String answer = loveApp.doChat(message, chatId);
        System.out.println(answer);
        // 第二轮
        message = "我现在在深圳，希望一个人玩三天，但是我不知道有什么好玩的，有什么好吃的，有什么好住的";
        answer = loveApp.doChat(message, chatId);
        System.out.println(answer);
        // 第三轮
        message = "我叫什么来着，刚刚好像和你说过，帮我回忆下";
        answer = loveApp.doChat(message, chatId);
        System.out.println(answer);
    }

}

