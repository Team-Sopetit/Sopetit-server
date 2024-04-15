package com.soptie.server.routine.service.daily.dto;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyTheme;

import lombok.Builder;

@Builder(access = PRIVATE)
public record DailyThemeListGetServiceResponse(
	List<DailyThemeServiceResponse> themes
) {

	public static DailyThemeListGetServiceResponse of(List<DailyTheme> themes) {
		return DailyThemeListGetServiceResponse.builder()
				.themes(themes.stream().map(DailyThemeServiceResponse::of).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	public record DailyThemeServiceResponse(
		long themeId,
		String name,
		String iconImageUrl,
		String backgroundImageUrl
	) {

		private static DailyThemeServiceResponse of(DailyTheme theme) {
			return DailyThemeServiceResponse.builder()
				.themeId(theme.getId())
				.name(theme.getName())
				.iconImageUrl(theme.getImageInfo().getIconImageUrl())
				.backgroundImageUrl(theme.getImageInfo().getBackgroundImageUrl())
				.build();
		}
	}
}
