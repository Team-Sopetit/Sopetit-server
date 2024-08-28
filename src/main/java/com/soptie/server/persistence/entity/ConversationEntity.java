package com.soptie.server.persistence.entity;

import com.soptie.server.domain.conversation.Conversation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "conversation", schema = "softie")
public class ConversationEntity extends BaseEntity {
	@Column(nullable = false)
	private String content;

	public Conversation toDomain() {
		return Conversation.builder()
			.id(this.id)
			.content(this.content)
			.build();
	}
}
