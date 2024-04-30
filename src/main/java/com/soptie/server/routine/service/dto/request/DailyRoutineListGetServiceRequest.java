package com.soptie.server.routine.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record DailyRoutineListGetServiceRequest(
		long memberId,
		long themeId
) {

	public static DailyRoutineListGetServiceRequest of(long memberId, long themeId) {
		return DailyRoutineListGetServiceRequest.builder()
				.memberId(memberId)
				.themeId(themeId)
				.build();
	}
}
