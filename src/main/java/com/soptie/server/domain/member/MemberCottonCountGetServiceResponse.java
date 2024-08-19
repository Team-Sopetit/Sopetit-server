package com.soptie.server.domain.member;

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
