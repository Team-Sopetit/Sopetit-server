package com.soptie.server.routine.controller.v2.dto.response;

import java.util.List;
import java.util.Map;

import com.soptie.server.routine.service.vo.ChallengeVO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeRoutineListAcquireResponseV2(
	List<RoutineResponse> routines
) {

	public static ChallengeRoutineListAcquireResponseV2 from(Map<String, List<ChallengeVO>> challengesMap) {
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
		List<ChallengeResponse> challenges
	) {

		private static RoutineResponse from(String title, List<ChallengeVO> challenges) {
			return RoutineResponse.builder()
				.title(title)
				.challenges(challenges.stream().map(ChallengeResponse::from).toList())
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

		private static ChallengeResponse from(ChallengeVO challenge) {
			return ChallengeResponse.builder()
				.challengeId(challenge.challengeId())
				.content(challenge.content())
				.description(challenge.description())
				.requiredTime(challenge.requiredTime())
				.place(challenge.place())
				.hasRoutine(challenge.hasRoutine())
				.build();
		}
	}
}
