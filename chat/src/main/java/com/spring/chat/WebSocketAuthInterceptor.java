package com.spring.chat;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Component
public class WebSocketAuthInterceptor implements ChannelInterceptor {

    private final JwtUtil jwtUtil;

    public WebSocketAuthInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor
                .getAccessor(message, StompHeaderAccessor.class);

        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authHeader = accessor.getFirstNativeHeader("Authorization");

            System.out.println("WebSocket CONNECT received");
            System.out.println("Auth header: " + authHeader);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("Missing or invalid auth header");
                return null;
            }

            String token = authHeader.substring(7);

            if (!jwtUtil.isTokenValid(token)) {
                System.out.println("Invalid token");
                return null;
            }

            String username = jwtUtil.extractUsername(token);
            System.out.println("Authenticated user: " + username);
            accessor.setUser(() -> username);
        }

        return message;
    }
}