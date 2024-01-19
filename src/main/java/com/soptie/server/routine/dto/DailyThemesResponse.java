package com.soptie.server.routine.dto;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyTheme;

import lombok.Builder;
import lombok.NonNull;

public record DailyThemesResponse(
	@NonNull List<DailyThemeResponse> themes
) {

	public static DailyThemesResponse of(List<DailyTheme> themes) {
		return new DailyThemesResponse(themes.stream().map(DailyThemeResponse::of).toList());
	}

	@Builder
	public record DailyThemeResponse(
		Long themeId,
		@NonNull String name,
		@NonNull String iconImageUrl,
		@NonNull String backgroundImageUrl
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
