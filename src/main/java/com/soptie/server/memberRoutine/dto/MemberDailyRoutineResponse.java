package com.soptie.server.memberRoutine.dto;

public record MemberDailyRoutineResponse(
	long routineId
) {

	public static MemberDailyRoutineResponse of(Long routineId) {
		return new MemberDailyRoutineResponse(routineId);
	}
}
