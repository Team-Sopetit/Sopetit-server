package com.soptie.server.member.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.member.service.dto.response.MemberCottonCountGetServiceResponse;

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
