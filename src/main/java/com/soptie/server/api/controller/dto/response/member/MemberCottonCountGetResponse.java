package com.soptie.server.api.controller.dto.response.member;

import static lombok.AccessLevel.*;

import com.soptie.server.domain.member.MemberCottonCountGetServiceResponse;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberCottonCountGetResponse(
	int cottonCount
) {

	public static MemberCottonCountGetResponse of(MemberCottonCountGetServiceResponse response) {
		return MemberCottonCountGetResponse.builder()
			.cottonCount(response.cottonCount())
			.build();
	}
}
