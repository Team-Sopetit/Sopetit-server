package com.soptie.server.conversation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.conversation.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
