package com.soptie.server.memberRoutine.dto;

import java.util.List;

import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

import lombok.Builder;

public record MemberDailyRoutinesResponse(
	List<MemberDailyRoutineResponse> routines
) {

	public static MemberDailyRoutinesResponse of(List<MemberDailyRoutine> routines) {
		return new MemberDailyRoutinesResponse(routines.stream().map(MemberDailyRoutineResponse::of).toList());
	}

	@Builder
	private record MemberDailyRoutineResponse(
		long routineId,
		String content,
		String iconImageUrl,
		int achieveCount,
		boolean isAchieve
	) {

		private static MemberDailyRoutineResponse of(MemberDailyRoutine routine) {
			return MemberDailyRoutineResponse.builder()
				.routineId(routine.getId())
				.content(routine.getRoutine().getContent())
				.iconImageUrl(routine.getRoutine().getTheme().getImageInfo().getIconImageUrl())
				.achieveCount(routine.getAchieveCount())
				.isAchieve(routine.isAchieve())
				.build();
		}
	}
}
