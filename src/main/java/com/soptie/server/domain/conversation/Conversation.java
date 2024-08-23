package com.soptie.server.domain.conversation;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Conversation {
	private Long id;
	@NotNull
	private String content;
}
