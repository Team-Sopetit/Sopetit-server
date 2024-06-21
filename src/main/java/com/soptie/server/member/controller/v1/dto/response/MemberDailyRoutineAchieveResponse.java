package com.soptie.server.member.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.member.service.dto.response.routine.MemberRoutineAchieveServiceResponse;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberDailyRoutineAchieveResponse(
	long routineId,
	boolean isAchieve,
	int achieveCount
) {

	public static MemberDailyRoutineAchieveResponse of(MemberRoutineAchieveServiceResponse response) {
		return MemberDailyRoutineAchieveResponse.builder()
			.routineId(response.routineId())
			.isAchieve(response.isAchieve())
			.achieveCount(response.achieveCount())
			.build();
	}
}
