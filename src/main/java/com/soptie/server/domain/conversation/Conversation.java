package com.soptie.server.domain.conversation;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class Conversation {
	private Long id;
	@NotNull
	private String content;
}
