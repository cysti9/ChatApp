package com.spring.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController
{
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String sendMessage(String message)
    {
        return message;
    }
    public ChatMessage sendMessage(ChatMessage message)
    {
        return message;
    }
}
