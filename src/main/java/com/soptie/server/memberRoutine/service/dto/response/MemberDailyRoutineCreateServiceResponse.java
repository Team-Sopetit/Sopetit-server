package com.soptie.server.memberroutine.service.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberroutine.entity.MemberRoutine;

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
