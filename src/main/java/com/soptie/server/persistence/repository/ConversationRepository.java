package com.soptie.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.ConversationEntity;

public interface ConversationRepository extends JpaRepository<ConversationEntity, Long> {
}
