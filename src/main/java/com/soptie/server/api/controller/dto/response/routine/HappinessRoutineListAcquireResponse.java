package com.soptie.server.api.controller.dto.response.routine;

import java.util.List;
import java.util.Objects;

import com.soptie.server.persistence.entity.Routine;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record HappinessRoutineListAcquireResponse(
	List<HappinessRoutineResponse> routines
) {

	public static HappinessRoutineListAcquireResponse from(List<Routine> routines) {
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

		private static HappinessRoutineResponse from(Routine routine) {
			return HappinessRoutineResponse.builder()
				.routineId(routine.getId())
				.name(Objects.nonNull(routine.getTheme()) ? routine.getTheme().getName() : "알 수 없는 테마")
				.nameColor(Objects.nonNull(routine.getTheme()) ? routine.getTheme().getColor() : "#FFFFFF")
				.title(routine.getContent())
				.iconImageUrl(routine.getTheme() != null ? routine.getTheme().getImageLinks().getIconImageUrl() : "")
				.build();
		}
	}
}
