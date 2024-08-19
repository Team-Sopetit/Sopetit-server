package com.soptie.server.domain.member;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.domain.doll.DollType;
import com.soptie.server.api.controller.dto.request.member.MemberProfileCreateRequest;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberProfileCreateServiceRequest(
	long memberId,
	@NonNull DollType dollType,
	@NonNull String name,
	@NonNull List<Long> routines
) {

	public static MemberProfileCreateServiceRequest of(long memberId, MemberProfileCreateRequest request) {
		return MemberProfileCreateServiceRequest.builder()
			.memberId(memberId)
			.dollType(request.dollType())
			.name(request.name())
			.routines(request.routines())
			.build();
	}
}
