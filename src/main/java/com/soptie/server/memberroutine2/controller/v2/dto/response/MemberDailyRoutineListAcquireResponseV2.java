package com.soptie.server.memberroutine2.controller.v2.dto.response;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.memberroutine2.service.dto.response.MemberDailyRoutineListAcquireServiceResponse;
import com.soptie.server.memberroutine2.service.dto.response.MemberDailyRoutinesAcquireServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListAcquireResponseV2(
	@NonNull List<MemberDailyRoutineListResponse> routines
) {

	public static MemberDailyRoutineListAcquireResponseV2 of(MemberDailyRoutineListAcquireServiceResponse response) {
		return MemberDailyRoutineListAcquireResponseV2.builder()
			.routines(response.routines().stream()
				.map(MemberDailyRoutineListResponse::of)
				.toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record MemberDailyRoutineListResponse(
		long themeId,
		@NonNull String themeName,
		@NonNull List<MemberDailyRoutineResponse> routines
	) {

		public static MemberDailyRoutineListResponse of(MemberDailyRoutinesAcquireServiceResponse routines) {
			return MemberDailyRoutineListResponse.builder()
				.themeId(routines.themeId())
				.themeName(routines.themeName())
				.routines(routines.routines().stream().map(MemberDailyRoutineResponse::of).toList())
				.build();
		}

		@Builder(access = PRIVATE)
		public record MemberDailyRoutineResponse(
			long routineId,
			@NonNull String content,
			int achieveCount,
			boolean isAchieve
		) {

			private static MemberDailyRoutineResponse of(
				MemberDailyRoutinesAcquireServiceResponse.MemberDailyRoutineServiceResponse routine
			) {
				return MemberDailyRoutineResponse.builder()
					.routineId(routine.routineId())
					.content(routine.content())
					.achieveCount(routine.achieveCount())
					.isAchieve(routine.isAchieve())
					.build();
			}
		}
	}
}
