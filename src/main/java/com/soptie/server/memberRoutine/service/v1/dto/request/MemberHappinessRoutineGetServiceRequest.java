package com.soptie.server.memberRoutine.service.v1.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberHappinessRoutineGetServiceRequest(
		long memberId
) {

	public static MemberHappinessRoutineGetServiceRequest of(long memberId) {
		return MemberHappinessRoutineGetServiceRequest.builder()
				.memberId(memberId)
				.build();
	}
}
