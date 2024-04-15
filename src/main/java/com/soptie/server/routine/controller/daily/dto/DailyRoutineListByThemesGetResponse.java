package com.soptie.server.routine.controller.daily.dto;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.routine.service.daily.dto.DailyRoutineListGetServiceResponse;
import com.soptie.server.routine.service.daily.dto.DailyRoutineListGetServiceResponse.DailyRoutineServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DailyRoutineListByThemesGetResponse(
		@NonNull List<DailyRoutineResponse> routines
) {

	public static DailyRoutineListByThemesGetResponse of(DailyRoutineListGetServiceResponse response) {
		return DailyRoutineListByThemesGetResponse.builder()
				.routines(response.routines().stream().map(DailyRoutineResponse::of).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	private record DailyRoutineResponse(
			long routineId,
			@NonNull String content
	) {

		private static DailyRoutineResponse of(DailyRoutineServiceResponse response) {
			return DailyRoutineResponse.builder()
					.routineId(response.routineId())
					.content(response.content())
					.build();
		}
	}
}
