package com.soptie.server.routine.dto.daily;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record DailyRoutinesByThemeGetResponse(
		@NonNull String backgroundImageUrl,
		@NonNull List<DailyRoutineResponse> routines
) {

	public static DailyRoutinesByThemeGetResponse of(DailyTheme theme, List<DailyRoutine> routines) {
		return new DailyRoutinesByThemeGetResponse(
				theme.getImageInfo().getBackgroundImageUrl(),
				routines.stream().map(DailyRoutineResponse::of).toList());
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
