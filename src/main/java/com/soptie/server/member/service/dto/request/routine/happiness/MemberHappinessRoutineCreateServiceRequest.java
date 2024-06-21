package com.soptie.server.member.service.dto.request.routine.happiness;

import static lombok.AccessLevel.*;

import com.soptie.server.member.controller.v1.dto.request.MemberHappinessRoutineRequest;

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
