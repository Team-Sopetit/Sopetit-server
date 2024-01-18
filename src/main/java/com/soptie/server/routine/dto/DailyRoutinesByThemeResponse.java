package com.soptie.server.routine.dto;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record DailyRoutinesByThemeResponse(
		@NonNull String backgroundImageUrl,
		@NonNull List<DailyRoutineResponse> routines
) {

	public static DailyRoutinesByThemeResponse of(DailyTheme theme, List<DailyRoutine> routines) {
		return new DailyRoutinesByThemeResponse(
				theme.getImageInfo().getBackgroundImageUrl(),
				routines.stream().map(DailyRoutineResponse::of).toList());
	}
}
