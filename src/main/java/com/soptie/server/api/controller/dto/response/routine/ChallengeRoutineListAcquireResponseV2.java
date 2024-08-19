package com.soptie.server.api.controller.dto.response.routine;

import java.util.List;
import java.util.Map;

import com.soptie.server.domain.routine.ChallengeRoutineListAcquireServiceResponse;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeRoutineListAcquireResponseV2(
	@NonNull List<RoutineResponse> routines
) {

	public static ChallengeRoutineListAcquireResponseV2 from(
		Map<String, ChallengeRoutineListAcquireServiceResponse> challengesMap
	) {
		return ChallengeRoutineListAcquireResponseV2.builder()
			.routines(challengesMap.keySet()
				.stream()
				.map(key -> RoutineResponse.from(key, challengesMap.get(key)))
				.toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record RoutineResponse(
		@NonNull String title,
		@NonNull List<ChallengeResponse> challenges
	) {

		private static RoutineResponse from(String title, ChallengeRoutineListAcquireServiceResponse challenges) {
			return RoutineResponse.builder()
				.title(title)
				.challenges(challenges.challenges().stream().map(ChallengeResponse::from).toList())
				.build();
		}
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record ChallengeResponse(
		long challengeId,
		@NonNull String content,
		@NonNull String description,
		@NonNull String requiredTime,
		@NonNull String place,
		boolean hasRoutine
	) {

		private static ChallengeResponse from(
			ChallengeRoutineListAcquireServiceResponse.ChallengeRoutineAcquireResponse challenge) {
			return ChallengeResponse.builder()
				.challengeId(challenge.challenge().challengeId())
				.content(challenge.challenge().content())
				.description(challenge.challenge().description())
				.requiredTime(challenge.challenge().requiredTime())
				.place(challenge.challenge().place())
				.hasRoutine(challenge.hasRoutine())
				.build();
		}
	}
}
