package com.soptie.server.member.service.dto.response.routine.daily;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.member.repository.dto.MemberRoutineResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetServiceResponse(
	@NonNull List<MemberDailyRoutineServiceResponse> routines
) {

	public static MemberDailyRoutineListGetServiceResponse of(List<MemberRoutineResponse> routines) {
		return MemberDailyRoutineListGetServiceResponse.builder()
			.routines(routines.stream().map(MemberDailyRoutineServiceResponse::of).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record MemberDailyRoutineServiceResponse(
		long routineId,
		@NonNull String content,
		@NonNull String iconImageUrl,
		int achieveCount,
		boolean isAchieve
	) {

		private static MemberDailyRoutineServiceResponse of(MemberRoutineResponse routine) {
			return MemberDailyRoutineServiceResponse.builder()
				.routineId(routine.id())
				.content(routine.content())
				.iconImageUrl(routine.dailyIconImageUrl())
				.achieveCount(routine.achieveCount())
				.isAchieve(routine.isAchieve())
				.build();
		}
	}
}
