package com.soptie.server.api.controller.memberroutine.dto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetMemberRoutinesResponse(
	@Schema(description = "회원의 루틴 정보 목록")
	@NotNull
	List<MemberRoutinesResponse> routines
) {

	public static GetMemberRoutinesResponse of(Map<Theme, List<MemberRoutine>> memberRoutinesByTheme) {
		return GetMemberRoutinesResponse.builder()
			.routines(memberRoutinesByTheme
				.entrySet()
				.stream()
				.map(entry -> MemberRoutinesResponse.of(entry.getKey(), entry.getValue()))
				.toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	public record MemberRoutinesResponse(
		@Schema(description = "테마 id", example = "1")
		long themeId,

		@Schema(description = "테마 이름", example = "관계 쌓기")
		@NotNull
		String themeName,

		@Schema(description = "루틴 정보 목록")
		@NotNull
		List<MemberRoutineResponse> routines
	) {

		public static MemberRoutinesResponse of(Theme theme, List<MemberRoutine> memberRoutines) {
			return MemberRoutinesResponse.builder()
				.themeId(theme.getId())
				.themeName(theme.getName())
				.routines(memberRoutines
					.stream()
					.sorted(Comparator.comparing(MemberRoutine::getId).reversed())
					.map(MemberRoutineResponse::of)
					.toList())
				.build();
		}
	}
}
