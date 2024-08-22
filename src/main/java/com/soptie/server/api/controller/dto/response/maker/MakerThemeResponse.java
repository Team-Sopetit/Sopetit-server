package com.soptie.server.api.controller.dto.response.maker;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.domain.maker.Maker;
import com.soptie.server.domain.maker.Tags;
import com.soptie.server.domain.theme.Theme;

import jakarta.validation.constraints.NotNull;

public record MakerThemeResponse(
	long themeId,
	@NotNull String description,
	@NotNull String name,
	@NotNull String comment,
	long makerId,
	@NotNull String makerName,
	@NotNull String introductionUrl,
	@NotNull String profileImageUrl,
	@NotNull Tags tags
) {

	@QueryProjection
	public MakerThemeResponse(Theme theme, Maker maker) {
		this(
			theme.getId(),
			theme.getDescription(),
			theme.getName(),
			theme.getComment(),
			maker.getId(),
			maker.getName(),
			maker.getIntroductionUrl(),
			maker.getProfileImageUrl(),
			maker.getTags()
		);
	}
}
