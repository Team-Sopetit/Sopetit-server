package com.soptie.server.domain.theme;

import static lombok.AccessLevel.*;

import com.soptie.server.persistence.entity.Theme;
import com.soptie.server.persistence.entity.ThemeType;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record ThemeVO(
	long themeId,
	@NonNull String name,
	@NonNull String modifier,
	@NonNull String description,
	@NonNull ThemeType themeType,
	@NonNull ThemeImageLinksVO imageLinks,
	@NonNull String color
) {

	public static ThemeVO from(Theme theme) {
		return ThemeVO.builder()
			.themeId(theme.getId())
			.name(theme.getName())
			.modifier(theme.getModifier())
			.description(theme.getDescription())
			.themeType(theme.getType())
			.imageLinks(ThemeImageLinksVO.from(theme.getImageLinks()))
			.color(theme.getColor())
			.build();
	}
}
