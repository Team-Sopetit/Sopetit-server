package com.soptie.server.persistence.adapter;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.conversation.Conversation;
import com.soptie.server.persistence.entity.ConversationEntity;
import com.soptie.server.persistence.repository.ConversationRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ConversationAdapter {
	private final ConversationRepository conversationRepository;

	public List<Conversation> findAll() {
		return conversationRepository.findAll().stream().map(ConversationEntity::toDomain).toList();
	}
}
