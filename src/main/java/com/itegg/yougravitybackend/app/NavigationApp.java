package com.itegg.yougravitybackend.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;


/**
 * AI调用测试模块
 * @author ITegg
 */
@Slf4j
@Component
public class NavigationApp {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMET = "你是旅游规划的资深导游。开场对用户表面身份，告知用户可以询问合适的旅游路线和打卡场所"
            + "围绕当前用户状态（单人/多人，是否饥饿，是否有精力打卡很多地点等)，引导用户提出自己期望的目标和个人状态，以便给出合适的出行方案";

    public NavigationApp(ChatModel dashChatClient) {
        // 初始化基于内存的对话记忆
        ChatMemory chatMemory = new InMemoryChatMemory();
        chatClient = ChatClient.builder(dashChatClient)
                .defaultSystem(SYSTEM_PROMET)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory)
                )
                .build();
    }

    public String doChat(String message, String chatId) {
        ChatResponse response = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .chatResponse();
        String content = response.getResult().getOutput().getText();
        log.info("response: {}", content);
        return content;
    }

}
