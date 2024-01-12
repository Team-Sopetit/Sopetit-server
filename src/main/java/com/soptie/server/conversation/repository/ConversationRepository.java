package com.soptie.server.conversation.repository;

import com.soptie.server.conversation.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
