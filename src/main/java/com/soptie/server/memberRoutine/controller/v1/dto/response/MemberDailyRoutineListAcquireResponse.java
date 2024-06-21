package com.soptie.server.memberRoutine.controller.v1.dto.response;

import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutinesAcquireServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutinesAcquireServiceResponse.MemberDailyRoutineServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

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

		private static MemberDailyRoutineResponse of(MemberDailyRoutineServiceResponse response) {
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
