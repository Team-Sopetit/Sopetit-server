package com.soptie.server.memberRoutine.dto;

import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

public record AchievedMemberDailyRoutineResponse(
	Long routineId,
	boolean isAchieve,
	int achieveCount
) {

	public static AchievedMemberDailyRoutineResponse of(MemberDailyRoutine routine) {
		return new AchievedMemberDailyRoutineResponse(routine.getId(), routine.isAchieve(), routine.getAchieveCount());
	}
}
