package com.soptie.server.domain.challenge;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Challenge {
	private Long id;
	@NotNull
	private String content;
	@NotNull
	private String description;
	@NotNull
	private String requiredTime;
	@NotNull
	private String place;
	private long themeId;
}
