package com.soptie.server.api.controller.dto.response.memberroutine;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberDailyRoutineCreateResponse(
	long routineId
) {

	public static MemberDailyRoutineCreateResponse of(MemberDailyRoutineCreateServiceResponse response) {
		return MemberDailyRoutineCreateResponse.builder()
			.routineId(response.routineId())
			.build();
	}
}
