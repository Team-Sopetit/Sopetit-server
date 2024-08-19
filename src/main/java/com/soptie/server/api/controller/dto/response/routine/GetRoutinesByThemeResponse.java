package com.soptie.server.api.controller.dto.response.routine;

import java.util.List;
import java.util.Map;

import com.soptie.server.domain.routine.Routine;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetRoutinesByThemeResponse(
	@Schema(description = "테마 정보 목록")
	List<ThemeResponse> themes
) {

	public static GetRoutinesByThemeResponse of(Map<Long, List<Routine>> routinesByThemeId) {
		return GetRoutinesByThemeResponse.builder()
			.themes(ofThemes(routinesByThemeId))
			.build();
	}

	private static List<ThemeResponse> ofThemes(Map<Long, List<Routine>> routinesByThemeId) {
		return routinesByThemeId.keySet().stream()
			.map(key -> ThemeResponse.of(key, routinesByThemeId.get(key)))
			.toList();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record ThemeResponse(
		@Schema(description = "테마 id", example = "1")
		long themeId,
		@Schema(description = "루틴 정보 목록")
		List<RoutineResponse> routines
	) {

		private static ThemeResponse of(long themeId, List<Routine> routines) {
			return ThemeResponse.builder()
				.themeId(themeId)
				.routines(routines.stream().map(RoutineResponse::of).toList())
				.build();
		}
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record RoutineResponse(
		@Schema(description = "루틴 id", example = "1")
		long routineId,
		@Schema(description = "루틴 내용", example = "1일 1커밋하기")
		@NotNull String content
	) {

		private static RoutineResponse of(Routine routine) {
			return RoutineResponse.builder()
				.routineId(routine.getId())
				.content(routine.getContent())
				.build();
		}
	}
}
