package com.soptie.server.routine.service.dto.request;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;

@Builder(access = PRIVATE)
public record HappinessRoutineListGetServiceRequest(
		Long themeId
) {

	public static HappinessRoutineListGetServiceRequest of(Long themeId) {
		return HappinessRoutineListGetServiceRequest.builder()
				.themeId(themeId)
				.build();
	}
}
