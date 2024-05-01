package com.soptie.server.routine.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record DailyRoutineListByThemeGetServiceRequest(
		long memberId,
		long themeId
) {

	public static DailyRoutineListByThemeGetServiceRequest of(long memberId, long themeId) {
		return DailyRoutineListByThemeGetServiceRequest.builder()
				.memberId(memberId)
				.themeId(themeId)
				.build();
	}
}
