package com.soptie.server.domain.memberroutine;

import static lombok.AccessLevel.*;

import com.soptie.server.persistence.entity.MemberRoutine;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberDailyRoutineCreateServiceResponse(
	long routineId
) {

	public static MemberDailyRoutineCreateServiceResponse of(MemberRoutine routine) {
		return MemberDailyRoutineCreateServiceResponse.builder()
			.routineId(routine.getId())
			.build();
	}
}
