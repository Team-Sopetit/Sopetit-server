package com.soptie.server.memberRoutine.service.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberDailyRoutineAchieveServiceResponse(
		long routineId,
		boolean isAchieve,
		int achieveCount
) {

	public static MemberDailyRoutineAchieveServiceResponse of(MemberDailyRoutine routine) {
		return MemberDailyRoutineAchieveServiceResponse.builder()
				.routineId(routine.getId())
				.isAchieve(routine.isAchieve())
				.achieveCount(routine.getAchieveCount())
				.build();
	}
}
