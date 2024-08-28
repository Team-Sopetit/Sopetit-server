package com.soptie.server.temporary.dto;

import java.util.List;

import com.soptie.server.domain.routine.Routine;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record DailyRoutinesResponse(
	@NonNull List<DailyRoutineResponse> routines
) {

	public static DailyRoutinesResponse of(List<Routine> routines) {
		return DailyRoutinesResponse.builder()
			.routines(routines.stream().map(DailyRoutineResponse::of).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record DailyRoutineResponse(
		long routineId,
		@NonNull String content
	) {

		private static DailyRoutineResponse of(Routine routine) {
			return DailyRoutineResponse.builder()
				.routineId(routine.getId())
				.content(routine.getContent())
				.build();
		}
	}
}
