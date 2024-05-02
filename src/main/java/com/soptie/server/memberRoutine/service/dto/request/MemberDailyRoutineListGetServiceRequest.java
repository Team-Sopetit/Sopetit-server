package com.soptie.server.memberRoutine.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetServiceRequest(
		long memberId
) {

	public static MemberDailyRoutineListGetServiceRequest of(long memberId) {
		return MemberDailyRoutineListGetServiceRequest.builder()
				.memberId(memberId)
				.build();
	}
}
