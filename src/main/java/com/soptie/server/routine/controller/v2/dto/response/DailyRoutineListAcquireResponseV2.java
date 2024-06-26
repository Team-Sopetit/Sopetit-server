package com.soptie.server.routine.controller.v2.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;
import java.util.Map;

import com.soptie.server.routine.service.vo.RoutineVO;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record DailyRoutineListAcquireResponseV2(
	List<ThemeResponse> themes
) {

	public static DailyRoutineListAcquireResponseV2 from(Map<Long, List<RoutineVO>> routinesMap) {
		return DailyRoutineListAcquireResponseV2.builder()
			.themes(routinesMap.keySet().stream().map(key -> ThemeResponse.from(key, routinesMap.get(key))).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record ThemeResponse(
		long themeId,
		List<RoutineResponse> routines
	) {

		private static ThemeResponse from(long themeId, List<RoutineVO> routines) {
			return ThemeResponse.builder()
				.themeId(themeId)
				.routines(routines.stream().map(RoutineResponse::from).toList())
				.build();
		}
	}

	@Builder(access = PRIVATE)
	private record RoutineResponse(
		long routineId,
		@NotNull String content
	) {

		private static RoutineResponse from(RoutineVO routine) {
			return RoutineResponse.builder()
				.routineId(routine.routineId())
				.content(routine.content())
				.build();
		}
	}
}
