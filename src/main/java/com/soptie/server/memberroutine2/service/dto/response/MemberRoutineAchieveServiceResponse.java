package com.soptie.server.memberroutine2.service.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberroutine2.entity.MemberRoutine;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberRoutineAchieveServiceResponse(
	long routineId,
	boolean isAchieve,
	int achieveCount
) {

	public static MemberRoutineAchieveServiceResponse of(MemberRoutine routine) {
		return MemberRoutineAchieveServiceResponse.builder()
			.routineId(routine.getId())
			.isAchieve(routine.isAchieve())
			.achieveCount(routine.getAchieveCount())
			.build();
	}
}
