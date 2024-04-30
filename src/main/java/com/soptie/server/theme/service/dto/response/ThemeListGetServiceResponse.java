package com.soptie.server.theme.service.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.theme.entity.Theme;

import lombok.Builder;

@Builder(access = PRIVATE)
public record ThemeListGetServiceResponse(
	List<DailyThemeServiceResponse> themes
) {

	public static ThemeListGetServiceResponse of(List<Theme> themes) {
		return ThemeListGetServiceResponse.builder()
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

		private static DailyThemeServiceResponse of(Theme theme) {
			return DailyThemeServiceResponse.builder()
				.themeId(theme.getId())
				.name(theme.getName())
				.iconImageUrl(theme.getImageInfo().getIconImageUrl())
				.backgroundImageUrl(theme.getImageInfo().getBackgroundImageUrl())
				.build();
		}
	}
}
