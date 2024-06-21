package com.soptie.server.member.service.dto.response.routine.daily;

import static lombok.AccessLevel.*;

import com.soptie.server.member.entity.MemberRoutine;

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
