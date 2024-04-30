package com.soptie.server.theme.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.theme.service.dto.response.ThemeListGetServiceResponse;
import com.soptie.server.theme.service.dto.response.ThemeListGetServiceResponse.ThemeServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record ThemeListGetResponse(
	@NonNull List<ThemeResponse> themes
) {

	public static ThemeListGetResponse of(ThemeListGetServiceResponse response) {
		return ThemeListGetResponse.builder()
				.themes(response.themes().stream().map(ThemeResponse::of).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	public record ThemeResponse(
		long themeId,
		@NonNull String name,
		@NonNull String iconImageUrl,
		@NonNull String backgroundImageUrl
	) {

		private static ThemeResponse of(ThemeServiceResponse response) {
			return ThemeResponse.builder()
				.themeId(response.themeId())
				.name(response.name())
				.iconImageUrl(response.iconImageUrl())
				.backgroundImageUrl(response.backgroundImageUrl())
				.build();
		}
	}
}
