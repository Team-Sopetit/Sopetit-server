package com.soptie.server.domain.member;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberHomeInfoGetServiceRequest(
	long memberId
) {

	public static MemberHomeInfoGetServiceRequest of(long memberId) {
		return MemberHomeInfoGetServiceRequest.builder()
			.memberId(memberId)
			.build();
	}
}
