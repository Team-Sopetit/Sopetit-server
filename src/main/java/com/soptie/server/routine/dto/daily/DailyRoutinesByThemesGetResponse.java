package com.soptie.server.routine.dto.daily;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyRoutine;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

public record DailyRoutinesByThemesGetResponse(
		@NonNull List<DailyRoutineResponse> routines
) {

	public static DailyRoutinesByThemesGetResponse of(List<DailyRoutine> routines) {
		return new DailyRoutinesByThemesGetResponse(routines.stream().map(DailyRoutineResponse::of).toList());
	}

	@Builder(access = AccessLevel.PRIVATE)
	public record DailyRoutineResponse(
			long routineId,
			@NonNull String content
	) {

		private static DailyRoutineResponse of(DailyRoutine routine) {
			return DailyRoutineResponse.builder()
					.routineId(routine.getId())
					.content(routine.getContent())
					.build();
		}
	}
}
