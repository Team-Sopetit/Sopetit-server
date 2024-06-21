package com.soptie.server.member.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.member.service.dto.response.routine.happiness.MemberHappinessRoutineCreateServiceResponse;

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
