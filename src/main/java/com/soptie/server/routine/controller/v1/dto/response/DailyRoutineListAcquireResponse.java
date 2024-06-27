package com.soptie.server.routine.controller.v1.dto.response;

import java.util.List;

import com.soptie.server.routine.service.vo.RoutineVO;
import com.soptie.server.theme.service.vo.ThemeVO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record DailyRoutineListAcquireResponse(
	String backgroundImageUrl,
	@NonNull List<DailyRoutineResponse> routines
) {

	public static DailyRoutineListAcquireResponse from(ThemeVO theme, List<RoutineVO> routines) {
		return DailyRoutineListAcquireResponse.builder()
			.backgroundImageUrl(theme.imageLinks().backgroundImageUrl())
			.routines(routines.stream().map(DailyRoutineResponse::from).toList())
			.build();
	}

	public static DailyRoutineListAcquireResponse from(List<RoutineVO> routines) {
		return DailyRoutineListAcquireResponse.builder()
			.routines(routines.stream().map(DailyRoutineResponse::from).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record DailyRoutineResponse(
		long routineId,
		@NonNull String content
	) {

		private static DailyRoutineResponse from(RoutineVO routine) {
			return DailyRoutineResponse.builder()
				.routineId(routine.routineId())
				.content(routine.content())
				.build();
		}
	}
}
