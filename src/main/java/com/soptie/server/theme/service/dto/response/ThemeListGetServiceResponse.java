package com.soptie.server.theme.service.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.theme.entity.Theme;

import lombok.Builder;

@Builder(access = PRIVATE)
public record ThemeListGetServiceResponse(
	List<ThemeServiceResponse> themes
) {

	public static ThemeListGetServiceResponse of(List<Theme> themes) {
		return ThemeListGetServiceResponse.builder()
				.themes(themes.stream().map(ThemeServiceResponse::of).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	public record ThemeServiceResponse(
		long themeId,
		String name,
		String iconImageUrl,
		String backgroundImageUrl,
		String dailyBackgroundImageUrl
	) {

		private static ThemeServiceResponse of(Theme theme) {
			return ThemeServiceResponse.builder()
				.themeId(theme.getId())
				.name(theme.getName())
				.iconImageUrl(theme.getImageInfo().getIconImageUrl())
				.backgroundImageUrl(theme.getImageInfo().getBackgroundImageUrl())
				.dailyBackgroundImageUrl(theme.getImageInfo().getDailyBackgroundImageUrl())
				.build();
		}
	}
}
