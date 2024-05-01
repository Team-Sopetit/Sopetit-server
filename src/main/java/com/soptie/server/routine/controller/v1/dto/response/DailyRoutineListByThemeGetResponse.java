package com.soptie.server.routine.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.routine.service.dto.response.DailyRoutineListGetServiceResponse;
import com.soptie.server.routine.service.dto.response.DailyRoutineListGetServiceResponse.DailyRoutineServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record DailyRoutineListByThemeGetResponse(
		@NonNull String backgroundImageUrl,
		@NonNull List<DailyRoutineResponse> routines
) {

	public static DailyRoutineListByThemeGetResponse of(DailyRoutineListGetServiceResponse response) {
		return DailyRoutineListByThemeGetResponse.builder()
				.backgroundImageUrl(response.backgroundImageUrl())
				.routines(response.routines().stream().map(DailyRoutineResponse::of).toList())
				.build();
	}

	@Builder(access = PRIVATE)
	public record DailyRoutineResponse(
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
