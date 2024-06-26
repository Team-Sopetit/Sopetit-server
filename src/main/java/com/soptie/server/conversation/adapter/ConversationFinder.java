package com.soptie.server.conversation.adapter;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.conversation.entity.Conversation;
import com.soptie.server.conversation.repository.ConversationRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ConversationFinder {

	private final ConversationRepository conversationRepository;

	public List<Conversation> findAll() {
		return conversationRepository.findAll();
	}
}
