package com.soptie.server.support.fixture;

public class ConversationFixture {

	private Long id;
	private String content;

	private ConversationFixture() {
	}

	public static ConversationFixture conversation() {
		return new ConversationFixture();
	}

	public ConversationFixture id(Long id) {
		this.id = id;
		return this;
	}

	public ConversationFixture content(String content) {
		this.content = content;
		return this;
	}

	public Conversation build() {
		return new Conversation(id, content);
	}
}
