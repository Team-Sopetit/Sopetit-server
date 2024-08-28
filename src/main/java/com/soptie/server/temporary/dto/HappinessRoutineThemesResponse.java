package com.soptie.server.temporary.dto;

import java.util.List;

import com.soptie.server.domain.theme.Theme;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record HappinessRoutineThemesResponse(
	@NonNull List<HappinessThemeResponse> themes
) {

	public static HappinessRoutineThemesResponse of(List<Theme> themes) {
		return HappinessRoutineThemesResponse.builder()
			.themes(themes.stream().map(HappinessThemeResponse::of).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record HappinessThemeResponse(
		long themeId,
		@NonNull String name
	) {

		private static HappinessThemeResponse of(Theme theme) {
			return HappinessThemeResponse.builder()
				.themeId(theme.getId())
				.name(theme.getName())
				.build();
		}
	}
}
