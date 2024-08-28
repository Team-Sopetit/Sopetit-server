package com.soptie.server.temporary.dto;

import java.util.List;

import com.soptie.server.domain.theme.Theme;
import com.soptie.server.temporary.data.ThemeData;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record DailyRoutineThemesResponse(
	@NonNull List<DailyThemeResponse> themes
) {

	public static DailyRoutineThemesResponse of(List<Theme> themes) {
		return DailyRoutineThemesResponse.builder()
			.themes(themes.stream().map(DailyThemeResponse::of).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	public record DailyThemeResponse(
		long themeId,
		@NonNull String name,
		@NonNull String iconImageUrl,
		@NonNull String backgroundImageUrl
	) {

		public static DailyThemeResponse of(Theme theme) {
			return DailyThemeResponse.builder()
				.themeId(theme.getId())
				.name(theme.getName())
				.iconImageUrl(ThemeData.getIconImageUrl(theme.getId()))
				.backgroundImageUrl(ThemeData.getDailyBackgroundImageUrl(theme.getId()))
				.build();
		}
	}
}
