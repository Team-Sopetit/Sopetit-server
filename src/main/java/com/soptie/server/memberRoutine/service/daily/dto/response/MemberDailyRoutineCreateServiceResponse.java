package com.soptie.server.memberRoutine.service.daily.dto.response;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberDailyRoutineCreateServiceResponse(
		long routineId
) {

	public static MemberDailyRoutineCreateServiceResponse of(MemberDailyRoutineCreateServiceResponse response) {
		return MemberDailyRoutineCreateServiceResponse.builder()
				.routineId(response.routineId)
				.build();
	}
}
