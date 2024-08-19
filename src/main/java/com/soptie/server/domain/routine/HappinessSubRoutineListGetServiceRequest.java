package com.soptie.server.domain.routine;

import static lombok.AccessLevel.PRIVATE;

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
