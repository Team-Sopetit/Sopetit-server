package com.soptie.server.domain.theme;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class Theme {
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String comment;
	@NotNull
	private String description;
	@NotNull
	private ThemeImageUrls imageUrls;
	private String color;
	private Long makerId;
}
