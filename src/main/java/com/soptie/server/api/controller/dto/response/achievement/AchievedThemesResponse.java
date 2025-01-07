package com.soptie.server.api.controller.dto.response.achievement;

import java.util.List;
import java.util.Map;

import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.val;

@Builder(access = AccessLevel.PRIVATE)
public record AchievedThemesResponse(
	@Schema(description = "테마 정보 목록")
	List<AchievedTheme> themes
) {

	public static AchievedThemesResponse of(
		List<Theme> themes,
		Map<Long, Integer> achievedCountsByTheme
	) {
		return AchievedThemesResponse.builder()
			.themes(themes.stream()
				.map(it -> AchievedTheme.of(it, achievedCountsByTheme.get(it.getId())))
				.sorted((a, b) -> {
					val diff = b.achievedCount - a.achievedCount;
					return diff != 0 ? diff : a.name.compareTo(b.name);
				})
				.toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record AchievedTheme(
		@Schema(description = "테마 id", example = "1")
		long id,
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
