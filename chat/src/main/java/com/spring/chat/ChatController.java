package com.spring.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ChatController
{
    private final MessageRepo messageRepo;

    public ChatController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage chatmessage)
    {
        Message message = new Message(chatmessage.getSender(), chatmessage.getContent(), LocalDateTime.now());
        messageRepo.save(message);
        chatmessage.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        return chatmessage;
    }

    @GetMapping("/history")
    @ResponseBody
    public List<Message> getHistory()
    {
        return messageRepo.findAllByOrderByTimestampAsc();
    }
}
