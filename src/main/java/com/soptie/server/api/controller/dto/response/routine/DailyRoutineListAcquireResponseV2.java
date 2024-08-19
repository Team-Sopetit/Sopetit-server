package com.soptie.server.api.controller.dto.response.routine;

import java.util.List;
import java.util.Map;

import com.soptie.server.persistence.entity.Routine;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record DailyRoutineListAcquireResponseV2(
	List<ThemeResponse> themes
) {

	public static DailyRoutineListAcquireResponseV2 from(Map<Long, List<Routine>> routinesMap) {
		return DailyRoutineListAcquireResponseV2.builder()
			.themes(routinesMap.keySet().stream().map(key -> ThemeResponse.from(key, routinesMap.get(key))).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record ThemeResponse(
		long themeId,
		List<RoutineResponse> routines
	) {

		private static ThemeResponse from(long themeId, List<Routine> routines) {
			return ThemeResponse.builder()
				.themeId(themeId)
				.routines(routines.stream().map(RoutineResponse::from).toList())
				.build();
		}
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record RoutineResponse(
		long routineId,
		@NotNull String content
	) {

		private static RoutineResponse from(Routine routine) {
			return RoutineResponse.builder()
				.routineId(routine.getId())
				.content(routine.getContent())
				.build();
		}
	}
}
