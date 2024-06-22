package com.soptie.server.member.service.dto.response;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberCottonCountGetServiceResponse(
	int cottonCount
) {

	public static MemberCottonCountGetServiceResponse of(int cottonCount) {
		return MemberCottonCountGetServiceResponse.builder()
			.cottonCount(cottonCount)
			.build();
	}
}
