package com.soptie.server.domain.memberroutine;

import static lombok.AccessLevel.*;

import java.util.List;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberRoutinesDeleteServiceRequest(
	long memberId,
	List<Long> routineIds
) {

	public static MemberRoutinesDeleteServiceRequest of(long memberId, List<Long> routineIds) {
		return MemberRoutinesDeleteServiceRequest.builder()
			.memberId(memberId)
			.routineIds(routineIds)
			.build();
	}
}
