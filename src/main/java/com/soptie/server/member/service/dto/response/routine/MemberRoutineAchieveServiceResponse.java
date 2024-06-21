package com.soptie.server.member.service.dto.response.routine;

import static lombok.AccessLevel.*;

import com.soptie.server.member.entity.MemberRoutine;

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
