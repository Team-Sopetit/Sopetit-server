package com.soptie.server.memberroutine2.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberroutine2.service.dto.response.MemberHappinessRoutineCreateServiceResponse;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberHappinessRoutineCreateResponse(
	long routineId
) {

	public static MemberHappinessRoutineCreateResponse of(MemberHappinessRoutineCreateServiceResponse response) {
		return MemberHappinessRoutineCreateResponse.builder()
			.routineId(response.routineId())
			.build();
	}
}
