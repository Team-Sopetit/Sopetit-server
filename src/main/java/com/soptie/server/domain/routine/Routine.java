package com.soptie.server.domain.routine;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Routine {
	private Long id;
	@NotNull
	private String content;
	private long themeId;
}
