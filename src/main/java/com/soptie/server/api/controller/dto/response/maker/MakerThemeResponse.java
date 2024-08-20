package com.soptie.server.api.controller.dto.response.maker;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.persistence.entity.deleted.Theme;

import lombok.NonNull;

public record MakerThemeResponse(
	long makerId,
	@NonNull String content,
	@NonNull String name,
	@NonNull String profileImageUrl,
	@NonNull List<String> tags,
	long themeId,
	@NonNull String description,
	@NonNull String themeName,
	@NonNull String modifier
) {

	@QueryProjection
	public MakerThemeResponse(Maker maker, Theme theme) {
		this(
			maker.getId(),
			maker.getContent(),
			maker.getName(),
			maker.getProfileImageUrl(),
			maker.getTags(),
			theme.getId(),
			theme.getDescription(),
			theme.getName(),
			theme.getModifier()
		);
	}
}