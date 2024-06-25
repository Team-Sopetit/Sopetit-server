package com.soptie.server.memberroutine2.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;

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
