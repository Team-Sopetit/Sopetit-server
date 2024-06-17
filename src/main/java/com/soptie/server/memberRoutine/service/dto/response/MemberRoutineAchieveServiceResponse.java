package com.soptie.server.memberRoutine.service.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberRoutine.entity.MemberRoutine;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberRoutineAchieveServiceResponse(
		long routineId,
		boolean isAchieve,
		int achieveCount,
		boolean isAchievedToday
) {

	public static MemberRoutineAchieveServiceResponse of(MemberRoutine routine, boolean isAchievedToday) {
		return MemberRoutineAchieveServiceResponse.builder()
				.routineId(routine.getId())
				.isAchieve(routine.isAchieve())
				.achieveCount(routine.getAchieveCount())
				.isAchievedToday(isAchievedToday)
				.build();
	}
}
