package com.soptie.server.routine.service.dto.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import lombok.Builder;

@Builder(access = PRIVATE)
public record DailyRoutineListByThemesGetServiceRequest(
		List<Long> themeIds
) {

	public static DailyRoutineListByThemesGetServiceRequest of(List<Long> themeIds) {
		return DailyRoutineListByThemesGetServiceRequest.builder()
				.themeIds(themeIds)
				.build();
	}
}
