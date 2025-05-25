package com.soptie.server.api.controller.memberroutine.dto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetMemberRoutinesResponse(
	@Schema(description = "회원의 루틴 정보 목록")
	@NotNull List<MemberRoutinesResponse> routines
) {

	public static GetMemberRoutinesResponse of(Map<Theme, Map<Routine, MemberRoutine>> routinesByTheme) {
		return GetMemberRoutinesResponse.builder()
			.routines(toMemberRoutines(routinesByTheme))
			.build();
	}

	private static List<MemberRoutinesResponse> toMemberRoutines(
		Map<Theme, Map<Routine, MemberRoutine>> routinesByTheme
	) {
		return routinesByTheme.entrySet().stream()
			.map(entry -> MemberRoutinesResponse.of(entry.getKey(), entry.getValue()))
			.toList();
	}

	@Builder(access = AccessLevel.PRIVATE)
	public record MemberRoutinesResponse(
		@Schema(description = "테마 id", example = "1")
		long themeId,
		@Schema(description = "테마 이름", example = "관계 쌓기")
		@NotNull String themeName,
		@Schema(description = "루틴 정보 목록")
		@NotNull List<MemberRoutineResponse> routines
	) {

		public static MemberRoutinesResponse of(Theme theme, Map<Routine, MemberRoutine> routinesByMember) {
			return MemberRoutinesResponse.builder()
				.themeId(theme.getId())
				.themeName(theme.getName())
				.routines(toRoutines(routinesByMember))
				.build();
		}

		private static List<MemberRoutineResponse> toRoutines(Map<Routine, MemberRoutine> routinesByMember) {
			return routinesByMember.entrySet().stream()
				.map(entry -> MemberRoutineResponse.of(entry.getKey(), entry.getValue()))
				.sorted(Comparator.comparingLong(MemberRoutineResponse::routineId))
				.toList();
		}

		@Builder(access = AccessLevel.PRIVATE)
		public record MemberRoutineResponse(
			@Schema(description = "회원 루틴 id", example = "1")
			long routineId,
			@Schema(description = "루틴 내용", example = "밥 먹기")
			@NotNull String content,
			@Schema(description = "달성 횟수", example = "10")
			int achieveCount,
			@Schema(description = "달성 여부", example = "true")
			boolean isAchieve
		) {

			private static MemberRoutineResponse of(Routine routine, MemberRoutine memberRoutine) {
				return MemberRoutineResponse.builder()
					.routineId(memberRoutine.getId())
					.content(routine.getContent())
					.achieveCount(memberRoutine.getAchievementCount())
					.isAchieve(memberRoutine.isAchieved())
					.build();
			}
		}
	}
}
