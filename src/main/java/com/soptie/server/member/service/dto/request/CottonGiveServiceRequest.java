package com.soptie.server.member.service.dto.request;

import static lombok.AccessLevel.*;

import com.soptie.server.member.entity.CottonType;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record CottonGiveServiceRequest(
	long memberId,
	@NonNull CottonType cottonType
) {

	public static CottonGiveServiceRequest of(long memberId, CottonType cottonType) {
		return CottonGiveServiceRequest.builder()
			.memberId(memberId)
			.cottonType(cottonType)
			.build();
	}
}
