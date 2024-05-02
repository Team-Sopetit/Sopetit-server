package com.soptie.server.memberRoutine.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.routine.entity.challenge.Challenge;
import com.soptie.server.theme.entity.Theme;

public record MemberChallengeResponse(
		Long id,
		String routineContent,
		String content,
		String description,
		String place,
		String requiredTime,
		Theme theme
) {

	@QueryProjection
	public MemberChallengeResponse(Challenge challenge, Theme theme) {
		this(
				challenge.getId(),
				challenge.getRoutine().getContent(),
				challenge.getContent(),
				challenge.getDescription(),
				challenge.getPlace(),
				challenge.getRequiredTime(),
				theme
		);
	}
}
