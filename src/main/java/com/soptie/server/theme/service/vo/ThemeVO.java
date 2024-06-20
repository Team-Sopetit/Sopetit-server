package com.soptie.server.theme.service.vo;

import static lombok.AccessLevel.*;

import com.soptie.server.theme.entity.Theme;
import com.soptie.server.theme.entity.ThemeType;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record ThemeVO(
		long themeId,
		@NonNull String name,
		@NonNull String modifier,
		@NonNull String description,
		ThemeType themeType
) {

	public static ThemeVO from(Theme theme) {
		return ThemeVO.builder()
				.themeId(theme.getId())
				.name(theme.getName())
				.modifier(theme.getModifier())
				.description(theme.getDescription())
				.themeType(theme.getType())
				.build();
	}
}
