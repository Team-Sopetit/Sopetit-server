package com.soptie.server.theme.service.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.theme.entity.Theme;

import lombok.Builder;

@Builder(access = PRIVATE)
public record ThemeListSearchServiceResponse(
	List<ThemeServiceResponse> themes
) {

	public static ThemeListSearchServiceResponse of(List<Theme> themes) {
		return ThemeListSearchServiceResponse.builder()
				.themes(themes.stream().map(ThemeServiceResponse::of).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	public record ThemeServiceResponse(
		long themeId,
		String name,
		String subName, //TODO: add
		String description, //TODO: add
		String iconImageUrl,
		String backgroundImageUrl,
		String dailyBackgroundImageUrl,
		String dailyIconImageUrl
	) {

		private static ThemeServiceResponse of(Theme theme) {
			return ThemeServiceResponse.builder()
				.themeId(theme.getId())
				.name(theme.getName())
				.iconImageUrl(theme.getImageInfo().getIconImageUrl())
				.backgroundImageUrl(theme.getImageInfo().getBackgroundImageUrl())
				.dailyBackgroundImageUrl(theme.getImageInfo().getDailyCardImageUrl())
				.dailyIconImageUrl(theme.getImageInfo().getDailyIconImageUrl())
				.build();
		}
	}
}
