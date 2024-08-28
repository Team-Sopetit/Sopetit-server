package com.soptie.server.temporary.dto;

import java.util.List;

import com.soptie.server.domain.routine.Routine;
import com.soptie.server.temporary.data.ThemeData;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record GetDailyRoutinesResponse(
	@NonNull String backgroundImageUrl,
	@NonNull List<DailyRoutineResponse> routines
) {

	public static GetDailyRoutinesResponse of(long themeId, List<Routine> routines) {
		return GetDailyRoutinesResponse.builder()
			.backgroundImageUrl(ThemeData.getDailyBackgroundImageUrl(themeId))
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
