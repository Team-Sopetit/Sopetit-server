package com.soptie.server.member.service.dto.request.routine.daily;

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
