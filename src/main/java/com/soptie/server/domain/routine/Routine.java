package com.soptie.server.domain.routine;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Routine {
	private Long id;
	@NotNull
	private String content;
	private long themeId;

	public Routine(String content, long themeId) {
		this.content = content;
		this.themeId = themeId;
	}
}
