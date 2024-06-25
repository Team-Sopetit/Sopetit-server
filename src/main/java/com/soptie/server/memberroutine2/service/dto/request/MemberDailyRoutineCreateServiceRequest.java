package com.soptie.server.memberroutine2.service.dto.request;

import static lombok.AccessLevel.*;

import com.soptie.server.memberroutine2.controller.v1.dto.request.MemberDailyRoutineCreateRequest;

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
