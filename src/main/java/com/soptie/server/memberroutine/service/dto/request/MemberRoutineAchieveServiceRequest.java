package com.soptie.server.memberroutine.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberRoutineAchieveServiceRequest(
	long memberId,
	long memberRoutineId
) {

	public static MemberRoutineAchieveServiceRequest of(long memberId, long memberRoutineId) {
		return MemberRoutineAchieveServiceRequest.builder()
			.memberId(memberId)
			.memberRoutineId(memberRoutineId)
			.build();
	}
}
