package com.soptie.server.theme.controller.v1.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.theme.service.dto.response.ThemeListGetServiceResponse;
import com.soptie.server.theme.service.dto.response.ThemeListGetServiceResponse.ThemeServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DailyThemeListGetResponse(
	@NonNull List<DailyThemeResponse> themes
) {

	public static DailyThemeListGetResponse of(ThemeListGetServiceResponse response) {
		return DailyThemeListGetResponse.builder()
				.themes(response.themes().stream().map(DailyThemeResponse::of).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	public record DailyThemeResponse(
		long themeId,
		@NonNull String name,
		@NonNull String iconImageUrl,
		@NonNull String backgroundImageUrl
	) {

		private static DailyThemeResponse of(ThemeServiceResponse response) {
			return DailyThemeResponse.builder()
				.themeId(response.themeId())
				.name(response.name())
				.iconImageUrl(response.dailyIconImageUrl())
				.backgroundImageUrl(response.dailyBackgroundImageUrl())
				.build();
		}
	}
}
