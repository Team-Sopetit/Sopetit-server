package com.soptie.server.memberRoutine.service.daily.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberDailyRoutineCreateServiceResponse(
		long routineId
) {

	public static MemberDailyRoutineCreateServiceResponse of(MemberDailyRoutine routine) {
		return MemberDailyRoutineCreateServiceResponse.builder()
				.routineId(routine.getId())
				.build();
	}
}
