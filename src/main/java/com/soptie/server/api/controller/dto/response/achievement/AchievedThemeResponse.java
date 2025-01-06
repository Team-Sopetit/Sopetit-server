package com.soptie.server.api.controller.dto.response.achievement;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AchievedThemeResponse(
	@Schema(description = "테마 id", example = "1")
	long id,
	@Schema(description = "테마 이름", example = "관계쌓기")
	String name,
	@Schema(description = "루틴 목록")
	List<AchievedRoutine> routines
) {

	public static AchievedThemeResponse of(
		Theme theme,
		List<Routine> routines,
		Map<Long, MemberRoutine> memberRoutinesByRoutine
	) {
		return AchievedThemeResponse.builder()
			.id(theme.getId())
			.name(theme.getName())
			.routines(routines.stream()
				.map(it -> AchievedRoutine.of(it, memberRoutinesByRoutine.get(it.getId())))
				.toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record AchievedRoutine(
		@Schema(description = "루틴 내용", example = "일찍 일어나기")
		String content,
		@Schema(description = "루틴 달성 횟수", example = "10")
		int achievedCount,
		@Schema(description = "루틴 시작일", example = "2024-01-01")
		LocalDate startedAt
	) {

		private static AchievedRoutine of(Routine routine, MemberRoutine memberRoutine) {
			return AchievedRoutine.builder()
				.content(routine.getContent())
				.achievedCount(memberRoutine.getAchievementCount())
				.startedAt(memberRoutine.getCreatedAt())
				.build();
		}
	}
}
