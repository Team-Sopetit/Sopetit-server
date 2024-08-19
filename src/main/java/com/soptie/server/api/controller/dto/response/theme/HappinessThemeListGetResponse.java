package com.soptie.server.api.controller.dto.response.theme;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.domain.theme.ThemeVO;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record HappinessThemeListGetResponse(@NonNull List<HappinessThemeResponse> themes) {

	public static HappinessThemeListGetResponse from(List<ThemeVO> themes) {
		return HappinessThemeListGetResponse.builder()
			.themes(themes.stream().map(HappinessThemeResponse::from).toList())
			.build();
	}

	@Builder
	public record HappinessThemeResponse(long themeId, @NonNull String name) {

		private static HappinessThemeResponse from(ThemeVO theme) {
			return HappinessThemeResponse.builder().themeId(theme.themeId()).name(theme.name()).build();
		}
	}
}
