package com.soptie.server.memberroutine.service.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListAcquireServiceResponse(
	@NonNull List<MemberDailyRoutinesAcquireServiceResponse> routines
) {

	public static MemberDailyRoutineListAcquireServiceResponse of(
		List<MemberDailyRoutinesAcquireServiceResponse> routines) {
		return MemberDailyRoutineListAcquireServiceResponse.builder()
			.routines(routines)
			.build();
	}
}
