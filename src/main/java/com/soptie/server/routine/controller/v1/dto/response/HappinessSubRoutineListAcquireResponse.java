package com.soptie.server.routine.controller.v1.dto.response;

import java.util.List;

import com.soptie.server.routine.service.dto.response.HappinessSubRoutineListGetServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessSubRoutineListGetServiceResponse.HappinessSubRoutineServiceResponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record HappinessSubRoutineListAcquireResponse(
	@NonNull String title,
	@NonNull String name,
	@NonNull String nameColor,
	@NonNull String iconImageUrl,
	@NonNull String contentImageUrl,
	@NonNull List<HappinessSubRoutineResponse> subRoutines
) {

	public static HappinessSubRoutineListAcquireResponse of(HappinessSubRoutineListGetServiceResponse response) {
		return HappinessSubRoutineListAcquireResponse.builder()
			.title(response.routineContent())
			.name(response.themeName())
			.nameColor(response.themeColor())
			.iconImageUrl(response.iconImageUrl())
			.contentImageUrl(response.backgroundImageUrl())
			.subRoutines(response.challenges().stream().map(HappinessSubRoutineResponse::of).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record HappinessSubRoutineResponse(
		long subRoutineId,
		@NonNull String content,
		@NonNull String detailContent,
		@NonNull String timeTaken,
		@NonNull String place
	) {

		private static HappinessSubRoutineResponse of(HappinessSubRoutineServiceResponse response) {
			return HappinessSubRoutineResponse.builder()
				.subRoutineId(response.challengeId())
				.content(response.content())
				.detailContent(response.description())
				.timeTaken(response.requiredTime())
				.place(response.place())
				.build();
		}
	}
}
