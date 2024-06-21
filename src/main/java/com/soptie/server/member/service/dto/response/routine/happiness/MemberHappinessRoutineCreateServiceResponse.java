package com.soptie.server.member.service.dto.response.routine.happiness;

import static lombok.AccessLevel.*;

import com.soptie.server.member.entity.MemberRoutine;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberHappinessRoutineCreateServiceResponse(
	long routineId
) {

	public static MemberHappinessRoutineCreateServiceResponse of(MemberRoutine memberRoutine) {
		return MemberHappinessRoutineCreateServiceResponse.builder()
			.routineId(memberRoutine.getId())
			.build();
	}
}
