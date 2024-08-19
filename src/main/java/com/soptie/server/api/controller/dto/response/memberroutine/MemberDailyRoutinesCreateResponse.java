package com.soptie.server.api.controller.dto.response.memberroutine;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.domain.memberroutine.MemberDailyRoutinesCreateServiceResponse;

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
