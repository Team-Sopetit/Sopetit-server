package com.soptie.server.memberRoutine.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.routine.entity.Routine;

public record MemberRoutineResponse(
		Long id,
		String content,
		String iconImageUrl,
		int achieveCount,
		boolean isAchieve
) {

	@QueryProjection
	public MemberRoutineResponse(MemberRoutine memberRoutine, Routine routine) {
		this(
				memberRoutine.getId(),
				routine.getContent(),
				routine.getTheme().getImageInfo().getIconImageUrl(),
				memberRoutine.getAchieveCount(),
				memberRoutine.isAchieve()
		);
	}
}
