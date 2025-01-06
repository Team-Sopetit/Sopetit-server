package com.soptie.server.api.controller.dto.response.achievement;

import java.util.List;
import java.util.Map;

import com.soptie.server.domain.theme.Theme;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AchievedThemesResponse(
	List<AchievedTheme> themes
) {

	public static AchievedThemesResponse of(
		List<Theme> themes,
		Map<Long, Integer> achievedCountsByTheme
	) {
		return AchievedThemesResponse.builder()
			.themes(themes.stream()
				.map(it -> AchievedTheme.of(it, achievedCountsByTheme.get(it.getId())))
				.toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record AchievedTheme(
		long id,
		String name,
		int achievedCount
	) {

		private static AchievedTheme of(Theme theme, int achievedCount) {
			return AchievedTheme.builder()
				.id(theme.getId())
				.name(theme.getName())
				.achievedCount(achievedCount)
				.build();
		}
	}
}
