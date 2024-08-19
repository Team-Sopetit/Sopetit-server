package com.soptie.server.persistence.adapter;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.persistence.entity.Conversation;
import com.soptie.server.persistence.repository.ConversationRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ConversationFinder {

	private final ConversationRepository conversationRepository;

	public List<Conversation> findAll() {
		return conversationRepository.findAll();
	}
}
