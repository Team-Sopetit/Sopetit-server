package com.soptie.server.api.controller.dto.response.memberroutine;

import static lombok.AccessLevel.*;

import com.soptie.server.domain.memberroutine.MemberHappinessRoutineCreateServiceResponse;

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
