package com.soptie.server.api.controller.dto.response.theme;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.domain.theme.ThemeVO;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record ThemeListAcquireResponse(
	@NonNull List<ThemeResponse> themes
) {

	public static ThemeListAcquireResponse from(List<ThemeVO> themes) {
		return ThemeListAcquireResponse.builder()
			.themes(themes.stream().map(ThemeResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record ThemeResponse(
		long themeId,
		@NonNull String title,
		@NonNull String subTitle,
		@NonNull String description
	) {

		private static ThemeResponse from(ThemeVO theme) {
			return ThemeResponse.builder()
				.themeId(theme.themeId())
				.title(theme.name())
				.subTitle(theme.modifier())
				.description(theme.description())
				.build();
		}
	}
}
