package com.soptie.server.memberRoutine.service.daily.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetServiceResponse(
	@NonNull List<MemberDailyRoutineServiceResponse> routines
) {

	public static MemberDailyRoutineListGetServiceResponse of(List<MemberDailyRoutine> routines) {
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

		private static MemberDailyRoutineServiceResponse of(MemberDailyRoutine routine) {
			return MemberDailyRoutineServiceResponse.builder()
				.routineId(routine.getId())
				.content(routine.getRoutine().getContent())
				.iconImageUrl(routine.getRoutine().getTheme().getImageInfo().getIconImageUrl())
				.achieveCount(routine.getAchieveCount())
				.isAchieve(routine.isAchieve())
				.build();
		}
	}
}
