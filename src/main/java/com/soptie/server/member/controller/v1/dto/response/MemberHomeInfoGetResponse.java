package com.soptie.server.member.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.doll.entity.DollType;
import com.soptie.server.member.service.dto.response.MemberHomeInfoGetServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberHomeInfoGetResponse(
	@NonNull String name,
	@NonNull DollType dollType,
	@NonNull String frameImageUrl,
	int dailyCottonCount,
	int happinessCottonCount,
	@NonNull List<String> conversations
) {

	public static MemberHomeInfoGetResponse of(MemberHomeInfoGetServiceResponse response) {
		return MemberHomeInfoGetResponse.builder()
			.name(response.name())
			.dollType(response.dollType())
			.frameImageUrl(response.frameImageUrl())
			.dailyCottonCount(response.dailyCottonCount())
			.happinessCottonCount(response.happinessCottonCount())
			.conversations(response.conversations())
			.build();
	}
}
