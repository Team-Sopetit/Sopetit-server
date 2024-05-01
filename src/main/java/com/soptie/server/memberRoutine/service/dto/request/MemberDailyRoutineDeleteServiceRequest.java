package com.soptie.server.memberRoutine.service.dto.request;

import static lombok.AccessLevel.*;

import java.util.List;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberDailyRoutineDeleteServiceRequest(
		long memberId,
		List<Long> routineIds
) {

	public static MemberDailyRoutineDeleteServiceRequest of(long memberId, List<Long> routineIds) {
		return MemberDailyRoutineDeleteServiceRequest.builder()
				.memberId(memberId)
				.routineIds(routineIds)
				.build();
	}
}
