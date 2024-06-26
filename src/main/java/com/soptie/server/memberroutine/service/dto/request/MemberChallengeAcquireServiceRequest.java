package com.soptie.server.memberroutine.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberChallengeAcquireServiceRequest(
	long memberId
) {

	public static MemberChallengeAcquireServiceRequest of(long memberId) {
		return MemberChallengeAcquireServiceRequest.builder()
			.memberId(memberId)
			.build();
	}
}
