package com.soptie.server.domain.memberroutine;

import static lombok.AccessLevel.*;

import com.soptie.server.persistence.entity.MemberRoutine;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberRoutineAchieveServiceResponse(
	long routineId,
	boolean isAchieve,
	int achieveCount,
	boolean hasCotton
) {

	public static MemberRoutineAchieveServiceResponse of(MemberRoutine routine, boolean hasCotton) {
		return MemberRoutineAchieveServiceResponse.builder()
			.routineId(routine.getId())
			.isAchieve(routine.isAchieve())
			.achieveCount(routine.getAchieveCount())
			.hasCotton(hasCotton)
			.build();
	}
}
