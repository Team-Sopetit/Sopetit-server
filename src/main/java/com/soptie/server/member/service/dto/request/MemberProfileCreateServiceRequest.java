package com.soptie.server.member.service.dto.request;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.doll.entity.DollType;
import com.soptie.server.member.controller.v1.dto.request.MemberProfileCreateRequest;

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
