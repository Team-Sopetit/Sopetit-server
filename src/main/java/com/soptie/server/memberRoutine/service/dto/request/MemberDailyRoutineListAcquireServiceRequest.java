package com.soptie.server.memberRoutine.service.dto.request;

import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListAcquireServiceRequest(
		long memberId
) {

	public static MemberDailyRoutineListAcquireServiceRequest of(long memberId) {
		return MemberDailyRoutineListAcquireServiceRequest.builder()
				.memberId(memberId)
				.build();
	}
}
