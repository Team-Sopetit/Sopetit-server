package com.soptie.server.domain.theme;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Theme {
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String comment;
	@NotNull
	private String description;
	private int sequence;
	private Long makerId;
}
