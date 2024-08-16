package com.soptie.server.memberroutine.controller.v2.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.memberroutine.service.dto.response.MemberDailyRoutinesCreateServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberDailyRoutinesCreateResponse(
	@NonNull List<Long> ids
) {

	public static MemberDailyRoutinesCreateResponse of(MemberDailyRoutinesCreateServiceResponse response) {
		return MemberDailyRoutinesCreateResponse.builder()
			.ids(response.ids())
			.build();
	}
}
