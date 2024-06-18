package com.soptie.server.memberRoutine.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberRoutineDeleteServiceRequest(
		long memberId,
		long routineId
) {

	public static MemberRoutineDeleteServiceRequest of(long memberId, long routineId) {
		return MemberRoutineDeleteServiceRequest.builder()
				.memberId(memberId)
				.routineId(routineId)
				.build();
	}
}
