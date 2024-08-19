package com.soptie.server.domain.memberroutine;

import static lombok.AccessLevel.*;

import com.soptie.server.api.controller.dto.request.memberroutine.MemberHappinessRoutineRequest;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberHappinessRoutineCreateServiceRequest(
	long memberId,
	long challengeId
) {

	public static MemberHappinessRoutineCreateServiceRequest of(long memberId, MemberHappinessRoutineRequest request) {
		return MemberHappinessRoutineCreateServiceRequest.builder()
			.memberId(memberId)
			.challengeId(request.subRoutineId())
			.build();
	}
}
