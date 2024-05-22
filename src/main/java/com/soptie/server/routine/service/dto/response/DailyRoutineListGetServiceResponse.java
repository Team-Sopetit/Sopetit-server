package com.soptie.server.routine.service.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.routine.entity.Routine;
import com.soptie.server.theme.entity.Theme;

import lombok.Builder;

@Builder(access = PRIVATE)
public record DailyRoutineListGetServiceResponse(
		List<DailyRoutineServiceResponse> routines,
		String backgroundImageUrl
) {

	public static DailyRoutineListGetServiceResponse of(List<Routine> routines) {
		return DailyRoutineListGetServiceResponse.builder()
				.routines(routines.stream().map(DailyRoutineServiceResponse::of).toList())
				.build();
	}

	public static DailyRoutineListGetServiceResponse of(List<Routine> routines, Theme theme) {
		return DailyRoutineListGetServiceResponse.builder()
				.routines(routines.stream().map(DailyRoutineServiceResponse::of).toList())
				.backgroundImageUrl(theme.getImageInfo().getDailyCardImageUrl())
				.build();
	}

	@Builder(access = PRIVATE)
	public record DailyRoutineServiceResponse(
			long routineId,
			String content
	) {

		private static DailyRoutineServiceResponse of(Routine routine) {
			return DailyRoutineServiceResponse.builder()
					.routineId(routine.getId())
					.content(routine.getContent())
					.build();
		}
	}
}
