package com.soptie.server.api.controller.dto.response.achievement;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.domain.theme.Theme;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AchievedThemeResponse(
	long id,
	String name,
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
		String content,
		int achievedCount,
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
