package com.soptie.server.domain.routine;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class Routine {
	private Long id;
	@NotNull
	private String content;
	private long themeId;
}
