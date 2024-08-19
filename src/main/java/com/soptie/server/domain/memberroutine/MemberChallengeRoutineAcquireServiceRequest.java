package com.soptie.server.domain.memberroutine;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberChallengeRoutineAcquireServiceRequest(
	long memberId
) {

	public static MemberChallengeRoutineAcquireServiceRequest of(long memberId) {
		return MemberChallengeRoutineAcquireServiceRequest.builder()
			.memberId(memberId)
			.build();
	}
}
