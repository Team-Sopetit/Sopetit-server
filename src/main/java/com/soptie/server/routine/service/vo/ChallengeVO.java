package com.soptie.server.routine.service.vo;

import com.soptie.server.routine.entity.Challenge;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengeVO(
	long challengeId,
	@NonNull String content,
	@NonNull String description,
	@NonNull String requiredTime,
	@NonNull String place,
	boolean hasRoutine
) {

	public static ChallengeVO from(Challenge challenge, long challengeId) {
		return ChallengeVO.builder()
			.challengeId(challenge.getId())
			.content(challenge.getContent())
			.description(challenge.getDescription())
			.requiredTime(challenge.getRequiredTime())
			.place(challenge.getPlace())
			.hasRoutine(challenge.getId().equals(challengeId))
			.build();
	}
}
