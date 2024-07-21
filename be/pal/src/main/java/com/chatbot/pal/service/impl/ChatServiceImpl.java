package com.chatbot.pal.service.impl;

import com.chatbot.pal.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder chatClientBuilder){
        chatClient = chatClientBuilder.build();
    }

}
