package com.soptie.server.domain.routine;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeVO(
	long challengeId,
	@NonNull String content,
	@NonNull String description,
	@NonNull String requiredTime,
	@NonNull String place
) {

	public static ChallengeVO from(Challenge challenge) {
		return ChallengeVO.builder()
			.challengeId(challenge.getId())
			.content(challenge.getContent())
			.description(challenge.getDescription())
			.requiredTime(challenge.getRequiredTime())
			.place(challenge.getPlace())
			.build();
	}
}
