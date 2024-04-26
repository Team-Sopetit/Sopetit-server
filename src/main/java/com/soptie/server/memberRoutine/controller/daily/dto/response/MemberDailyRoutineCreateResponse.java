package com.soptie.server.memberRoutine.controller.daily.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberRoutine.service.daily.dto.response.MemberDailyRoutineCreateServiceResponse;

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
