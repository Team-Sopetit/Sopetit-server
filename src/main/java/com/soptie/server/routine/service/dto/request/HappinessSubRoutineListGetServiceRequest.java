package com.soptie.server.routine.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record HappinessSubRoutineListGetServiceRequest(
		long routineId
) {

	public static HappinessSubRoutineListGetServiceRequest of(long routineId) {
		return HappinessSubRoutineListGetServiceRequest.builder()
				.routineId(routineId)
				.build();
	}
}
