package com.soptie.server.routine.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = PRIVATE)
public record HappinessRoutineListGetServiceRequest(
		long themeId
) {

	public static HappinessRoutineListGetServiceRequest of(long themeId) {
		return HappinessRoutineListGetServiceRequest.builder()
				.themeId(themeId)
				.build();
	}
}
