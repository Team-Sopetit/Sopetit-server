package com.soptie.server.memberRoutine.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberDailyRoutineAchieveServiceRequest(
		long memberId,
		long memberRoutineId
) {

	public static MemberDailyRoutineAchieveServiceRequest of(long memberId, long memberRoutineId) {
		return MemberDailyRoutineAchieveServiceRequest.builder()
				.memberId(memberId)
				.memberRoutineId(memberRoutineId)
				.build();
	}
}
