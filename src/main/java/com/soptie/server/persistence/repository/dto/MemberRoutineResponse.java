package com.soptie.server.persistence.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.persistence.entity.MemberRoutine;
import com.soptie.server.persistence.entity.Routine;

import lombok.NonNull;

public record MemberRoutineResponse(
	long id,
	@NonNull String content,
	@NonNull String iconImageUrl,
	@NonNull String dailyIconImageUrl,
	long themeId,
	@NonNull String themeName,
	int achieveCount,
	boolean isAchieve
) {

	@QueryProjection
	public MemberRoutineResponse(MemberRoutine memberRoutine, Routine routine) {
		this(
			memberRoutine.getId(),
			routine.getContent(),
			routine.getTheme().getImageLinks().getIconImageUrl(),
			routine.getTheme().getImageLinks().getDailyIconImageUrl(),
			routine.getTheme().getId(),
			routine.getTheme().getName(),
			memberRoutine.getAchieveCount(),
			memberRoutine.isAchieve()
		);
	}
}
