package com.soptie.server.routine.dto;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyTheme;

import lombok.Builder;

public record DailyThemesResponse(
	List<DailyThemeResponse> themes
) {

	public static DailyThemesResponse of(List<DailyTheme> themes) {
		return new DailyThemesResponse(themes.stream().map(DailyThemeResponse::of).toList());
	}

	@Builder
	public record DailyThemeResponse(
		Long themeId,
		String name,
		String iconImageUrl,
		String backgroundImageUrl
	) {
		private static DailyThemeResponse of(DailyTheme theme) {
			return DailyThemeResponse.builder()
				.themeId(theme.getId())
				.name(theme.getName())
				.iconImageUrl(theme.getImageInfo().getIconImageUrl())
				.backgroundImageUrl(theme.getImageInfo().getBackgroundImageUrl())
				.build();
		}
	}
}
