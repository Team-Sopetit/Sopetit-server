package com.soptie.server.domain.routine;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record ChallengeRoutineListAcquireServiceResponse(
	@NonNull List<ChallengeRoutineAcquireResponse> challenges
) {

	public static ChallengeRoutineListAcquireServiceResponse of(List<ChallengeVO> challenges, long challengeId) {
		return ChallengeRoutineListAcquireServiceResponse.builder()
			.challenges(challenges.stream()
				.map(challenge -> ChallengeRoutineAcquireResponse.of(challenge, challengeId))
				.toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record ChallengeRoutineAcquireResponse(
		ChallengeVO challenge,
		boolean hasRoutine
	) {

		public static ChallengeRoutineAcquireResponse of(ChallengeVO challenge, long challengeId) {
			return ChallengeRoutineAcquireResponse.builder()
				.challenge(challenge)
				.hasRoutine(challenge.challengeId() == challengeId)
				.build();
		}
	}
}
