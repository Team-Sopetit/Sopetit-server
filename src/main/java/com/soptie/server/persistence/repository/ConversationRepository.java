package com.soptie.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
