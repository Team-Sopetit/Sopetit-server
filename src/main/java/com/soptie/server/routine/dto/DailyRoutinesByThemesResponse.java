package com.soptie.server.routine.dto;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyRoutine;

import lombok.NonNull;

public record DailyRoutinesByThemesResponse(
		@NonNull List<DailyRoutineResponse> routines
) {

	public static DailyRoutinesByThemesResponse of(List<DailyRoutine> routines) {
		return new DailyRoutinesByThemesResponse(routines.stream().map(DailyRoutineResponse::of).toList());
	}
}
