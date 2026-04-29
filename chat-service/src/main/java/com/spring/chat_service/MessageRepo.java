package com.spring.chat_service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, String>
{
    List<Message> findAllByOrderByTimestampAsc();
}