package com.soptie.server.memberRoutine.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.routine.entity.Routine;

public record MemberRoutineResponse(
		Long id,
		String content,
		String iconImageUrl,
		String dailyIconImageUrl,
		Long themeId,
		String themeName,
		int achieveCount,
		boolean isAchieve
) {

	@QueryProjection
	public MemberRoutineResponse(MemberRoutine memberRoutine, Routine routine) {
		this(
				memberRoutine.getId(),
				routine.getContent(),
				routine.getTheme().getImageInfo().getIconImageUrl(),
				routine.getTheme().getImageInfo().getDailyIconImageUrl(),
				routine.getTheme().getId(),
				routine.getTheme().getName(),
				memberRoutine.getAchieveCount(),
				memberRoutine.isAchieve()
		);
	}
}
