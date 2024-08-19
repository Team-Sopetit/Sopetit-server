package com.soptie.server.domain.memberroutine;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.persistence.repository.dto.MemberRoutineResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberDailyRoutinesAcquireServiceResponse(
	long themeId,
	@NonNull String themeName,
	@NonNull List<MemberDailyRoutineServiceResponse> routines
) {

	public static MemberDailyRoutinesAcquireServiceResponse of(List<MemberRoutineResponse> routines) {
		return MemberDailyRoutinesAcquireServiceResponse.builder()
			.themeId(routines.get(0).themeId())
			.themeName(routines.get(0).themeName())
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
