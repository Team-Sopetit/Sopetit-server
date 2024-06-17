package com.soptie.server.memberRoutine.controller.v2.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberRoutine.service.dto.response.MemberRoutineAchieveServiceResponse;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberChallengeRoutineAchieveResponse(
	long routineId,
	boolean isAchieve,
	int achieveCount,
	boolean isAchievedToday
) {

	public static MemberChallengeRoutineAchieveResponse of(MemberRoutineAchieveServiceResponse response) {
		return MemberChallengeRoutineAchieveResponse.builder()
				.routineId(response.routineId())
				.isAchieve(response.isAchieve())
				.achieveCount(response.achieveCount())
				.isAchievedToday(response.isAchievedToday())
				.build();
	}
}
