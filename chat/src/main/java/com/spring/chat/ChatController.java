package com.spring.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ChatController
{
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")

    public ChatMessage sendMessage(ChatMessage message)
    {
        message.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        return message;
    }
}
