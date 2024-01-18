package com.soptie.server.routine.dto;

import com.soptie.server.routine.entity.daily.DailyRoutine;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PROTECTED)
public record DailyRoutineResponse(
		Long routineId,
		@NonNull String content
) {

	protected static DailyRoutineResponse of(DailyRoutine routine) {
		return DailyRoutineResponse.builder()
				.routineId(routine.getId())
				.content(routine.getContent())
				.build();
	}
}
