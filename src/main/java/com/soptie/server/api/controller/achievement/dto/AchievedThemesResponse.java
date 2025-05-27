package com.soptie.server.api.controller.achievement.dto;

import java.util.List;
import java.util.Map;

import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AchievedThemesResponse(
	@Schema(description = "전체 달성 횟수", example = "100")
	int achievedCount,

	@NotNull
	@Schema(description = "테마 정보 목록")
	List<AchievedTheme> themes
) {

	public static AchievedThemesResponse from(Map<Theme, Integer> achievedThemes) {
		List<AchievedTheme> themes = achievedThemes.entrySet()
			.stream()
			.map(entry -> AchievedTheme.of(entry.getKey(), entry.getValue()))
			.toList();

		return AchievedThemesResponse.builder()
			.achievedCount(themes.stream().mapToInt(it -> it.achievedCount).sum())
			.themes(themes)
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record AchievedTheme(
		@Schema(description = "테마 id", example = "1")
		long id,

		@NotNull
		@Schema(description = "테마 이름", example = "관계쌓기")
		String name,

		@Schema(description = "테마를 달성한 횟수", example = "50")
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
