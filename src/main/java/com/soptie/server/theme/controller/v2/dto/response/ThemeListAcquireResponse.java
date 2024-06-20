package com.soptie.server.theme.controller.v2.dto.response;

import java.util.List;

import com.soptie.server.theme.service.vo.ThemeVO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record ThemeListAcquireResponse(
		@NonNull List<ThemeResponse> themes
) {

	public static ThemeListAcquireResponse from(List<ThemeVO> themes) {
		return ThemeListAcquireResponse.builder()
				.themes(themes.stream().map(ThemeResponse::from).toList())
				.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
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
