package com.soptie.server.theme.controller.v1.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.theme.service.vo.ThemeVO;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DailyThemeListGetResponse(
	@NonNull List<DailyThemeResponse> themes
) {

	public static DailyThemeListGetResponse from(List<ThemeVO> themes) {
		return DailyThemeListGetResponse.builder()
				.themes(themes.stream().map(DailyThemeResponse::from).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	private record DailyThemeResponse(
		long themeId,
		@NonNull String name,
		@NonNull String iconImageUrl,
		@NonNull String backgroundImageUrl
	) {

		private static DailyThemeResponse from(ThemeVO theme) {
			return DailyThemeResponse.builder()
					.themeId(theme.themeId())
					.name(theme.name())
					.iconImageUrl(theme.imageInfo().getDailyIconImageUrl())
					.backgroundImageUrl(theme.imageInfo().getDailyCardImageUrl())
					.build();
		}
	}
}
