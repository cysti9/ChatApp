package com.spring.chat_service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatMessage
{
    private String sender;
    private String content;
    private String timestamp;
    ChatMessage(){}

    public ChatMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public String getSender() {
        return sender;
    }
    public String getContent() {
        return content;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}