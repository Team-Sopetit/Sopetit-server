package com.soptie.server.memberroutine2.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.memberroutine2.service.dto.response.MemberDailyRoutinesAcquireServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListAcquireResponse(
	@NonNull List<MemberDailyRoutineResponse> routines
) {

	public static MemberDailyRoutineListAcquireResponse of(MemberDailyRoutinesAcquireServiceResponse response) {
		return MemberDailyRoutineListAcquireResponse.builder()
			.routines(response.routines().stream().map(MemberDailyRoutineResponse::of).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record MemberDailyRoutineResponse(
		long routineId,
		@NonNull String content,
		@NonNull String iconImageUrl,
		int achieveCount,
		boolean isAchieve
	) {

		private static MemberDailyRoutineResponse of(
			MemberDailyRoutinesAcquireServiceResponse.MemberDailyRoutineServiceResponse response) {
			return MemberDailyRoutineResponse.builder()
				.routineId(response.routineId())
				.content(response.content())
				.iconImageUrl(response.iconImageUrl())
				.achieveCount(response.achieveCount())
				.isAchieve(response.isAchieve())
				.build();
		}
	}
}
