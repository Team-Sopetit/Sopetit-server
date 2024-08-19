package com.soptie.server.domain.memberroutine;

import static lombok.AccessLevel.*;

import com.soptie.server.api.controller.dto.request.memberroutine.MemberDailyRoutineCreateRequest;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberDailyRoutineCreateServiceRequest(
	long memberId,
	long routineId
) {

	public static MemberDailyRoutineCreateServiceRequest of(long memberId, MemberDailyRoutineCreateRequest request) {
		return MemberDailyRoutineCreateServiceRequest.builder()
			.memberId(memberId)
			.routineId(request.routineId())
			.build();
	}
}
