package com.soptie.server.routine.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.routine.service.dto.response.HappinessRoutineListGetServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessRoutineListGetServiceResponse.HappinessRoutineServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record HappinessRoutineListGetResponse(List<HappinessRoutineResponse> routines) {

	public static HappinessRoutineListGetResponse of(HappinessRoutineListGetServiceResponse response) {
		return HappinessRoutineListGetResponse.builder()
			.routines(response.routines().stream().map(HappinessRoutineResponse::of).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record HappinessRoutineResponse(
		long routineId, @NonNull String name, @NonNull String nameColor,
		@NonNull String title, @NonNull String iconImageUrl
	) {

		public static HappinessRoutineResponse of(HappinessRoutineServiceResponse response) {
			return HappinessRoutineResponse.builder()
				.routineId(response.routineId())
				.name(response.themeName())
				.nameColor(response.themeColor())
				.title(response.content())
				.iconImageUrl(response.iconImageUrl())
				.build();
		}
	}
}
