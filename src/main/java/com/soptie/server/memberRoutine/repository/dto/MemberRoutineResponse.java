package com.soptie.server.memberRoutine.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.routine.entity.Routine;

public record MemberRoutineResponse(
		Long id,
		String content,
		String iconImageUrl,
		String dailyIconImageUrl,
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
				memberRoutine.getAchieveCount(),
				memberRoutine.isAchieve()
		);
	}
}
