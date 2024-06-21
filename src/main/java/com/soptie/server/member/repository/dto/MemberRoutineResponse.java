package com.soptie.server.member.repository.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.member.entity.MemberRoutine;
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
			routine.getTheme().getImageInfo().getIconImageUrl(),
			routine.getTheme().getImageInfo().getDailyIconImageUrl(),
			memberRoutine.getAchieveCount(),
			memberRoutine.isAchieve()
		);
	}
}
