package com.soptie.server.routine.service.daily.dto;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;

import lombok.Builder;

@Builder(access = PRIVATE)
public record DailyRoutineListGetServiceResponse(
		List<DailyRoutineServiceResponse> routines,
		String backgroundImageUrl
) {

	public static DailyRoutineListGetServiceResponse of(List<DailyRoutine> routines) {
		return DailyRoutineListGetServiceResponse.builder()
				.routines(routines.stream().map(DailyRoutineServiceResponse::of).toList())
				.build();
	}

	public static DailyRoutineListGetServiceResponse of(List<DailyRoutine> routines, DailyTheme theme) {
		return DailyRoutineListGetServiceResponse.builder()
				.routines(routines.stream().map(DailyRoutineServiceResponse::of).toList())
				.backgroundImageUrl(theme.getImageInfo().getBackgroundImageUrl())
				.build();
	}

	@Builder(access = PRIVATE)
	public record DailyRoutineServiceResponse(
			long routineId,
			String content
	) {

		private static DailyRoutineServiceResponse of(DailyRoutine routine) {
			return DailyRoutineServiceResponse.builder()
					.routineId(routine.getId())
					.content(routine.getContent())
					.build();
		}
	}
}
