package com.soptie.server.persistence.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.persistence.entity.deleted.MemberRoutine;
import com.soptie.server.persistence.entity.deleted.Theme;

import lombok.NonNull;

public record MemberChallengeResponse(
	long id,
	long challengeId,
	@NonNull String routineContent,
	@NonNull String content,
	@NonNull String description,
	@NonNull String place,
	@NonNull String requiredTime,
	@NonNull Theme theme
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
