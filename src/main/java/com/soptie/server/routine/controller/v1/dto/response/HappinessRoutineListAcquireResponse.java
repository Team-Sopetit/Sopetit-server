package com.soptie.server.routine.controller.v1.dto.response;

import java.util.List;
import java.util.Objects;

import com.soptie.server.routine.service.vo.RoutineVO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record HappinessRoutineListAcquireResponse(
	List<HappinessRoutineResponse> routines
) {

	public static HappinessRoutineListAcquireResponse from(List<RoutineVO> routines) {
		return HappinessRoutineListAcquireResponse.builder()
			.routines(routines.stream().map(HappinessRoutineResponse::from).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record HappinessRoutineResponse(
		long routineId,
		@NonNull String name,
		@NonNull String nameColor,
		@NonNull String title,
		@NonNull String iconImageUrl
	) {

		private static HappinessRoutineResponse from(RoutineVO routine) {
			return HappinessRoutineResponse.builder()
				.routineId(routine.routineId())
				.name(Objects.nonNull(routine.theme()) ? routine.theme().name() : "알 수 없는 테마")
				.nameColor(Objects.nonNull(routine.theme()) ? routine.theme().color() : "#FFFFFF")
				.title(routine.content())
				.iconImageUrl(Objects.nonNull(routine.theme()) ? routine.theme().imageLinks().iconImageUrl() : "")
				.build();
		}
	}
}
