package com.soptie.server.memberroutine.service.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.memberroutine.entity.MemberRoutine;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberDailyRoutinesCreateServiceResponse(
	@NonNull List<Long> ids
) {

	public static MemberDailyRoutinesCreateServiceResponse of(List<MemberRoutine> memberRoutines) {
		return MemberDailyRoutinesCreateServiceResponse.builder()
			.ids(memberRoutines.stream().map(MemberRoutine::getId).toList())
			.build();
	}
}
