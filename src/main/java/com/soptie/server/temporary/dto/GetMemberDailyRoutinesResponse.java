package com.soptie.server.temporary.dto;

import java.util.List;
import java.util.Map;

import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.temporary.data.ThemeData;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record GetMemberDailyRoutinesResponse(
	List<MemberDailyRoutineResponse> routines
) {

	public static GetMemberDailyRoutinesResponse of(Map<MemberRoutine, Routine> map) {
		return GetMemberDailyRoutinesResponse.builder()
			.routines(map.entrySet().stream()
				.map(it -> MemberDailyRoutineResponse.of(it.getKey(), it.getValue())).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record MemberDailyRoutineResponse(
		long routineId,
		@NonNull String content,
		@NonNull String iconImageUrl,
		int achieveCount,
		boolean isAchieve
	) {

		private static MemberDailyRoutineResponse of(MemberRoutine memberRoutine, Routine routine) {
			return MemberDailyRoutineResponse.builder()
				.routineId(memberRoutine.getId())
				.content(routine.getContent())
				.iconImageUrl(ThemeData.getIconImageUrl(routine.getThemeId()))
				.achieveCount(memberRoutine.getAchievementCount())
				.isAchieve(memberRoutine.isAchieved())
				.build();
		}
	}
}
