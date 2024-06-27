package com.soptie.server.memberroutine.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.memberroutine.entity.MemberRoutine;
import com.soptie.server.routine.entity.Challenge;
import com.soptie.server.theme.entity.Theme;

public record MemberChallengeResponse(
	Long id,
	Long challengeId,
	String routineContent,
	String content,
	String description,
	String place,
	String requiredTime,
	Theme theme
) {

	@QueryProjection
	public MemberChallengeResponse(MemberRoutine memberRoutine, Challenge challenge, Theme theme) {
		this(
			memberRoutine.getId(),
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
