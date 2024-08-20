package com.soptie.server.domain.challenge;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Challenge {
	private Long id;
	@NotNull
	private String name;
	private long themeId;
}
