package com.soptie.server.routine.dto;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyRoutine;

import lombok.Builder;

public record DailyRoutinesResponse(
	List<DailyRoutineResponse> routines
) {

	public static DailyRoutinesResponse of(List<DailyRoutine> routines) {
		return new DailyRoutinesResponse(routines.stream().map(DailyRoutineResponse::of).toList());
	}

	@Builder
	private record DailyRoutineResponse(
		Long routineId,
		String content
	) {

		private static DailyRoutineResponse of(DailyRoutine routine) {
			return DailyRoutineResponse.builder()
				.routineId(routine.getId())
				.content(routine.getContent())
				.build();
		}
	}
}
