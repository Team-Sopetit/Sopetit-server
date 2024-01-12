package com.soptie.server.memberRoutine.dto;

import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

public record MemberDailyRoutineResponse(
	long routineId
) {

	public static MemberDailyRoutineResponse of(MemberDailyRoutine routine) {
		return new MemberDailyRoutineResponse(routine.getId());
	}
}
