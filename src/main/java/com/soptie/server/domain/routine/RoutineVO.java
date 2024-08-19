package com.soptie.server.domain.routine;

import com.soptie.server.persistence.entity.deleted.Theme;
import com.soptie.server.domain.theme.ThemeVO;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record RoutineVO(
	long routineId,
	@NotNull String content,
	@NotNull RoutineType routineType,
	ThemeVO theme
) {

	public static RoutineVO from(Routine routine) {
		return RoutineVO.builder()
			.routineId(routine.getId())
			.content(routine.getContent())
			.routineType(routine.getType())
			.build();
	}

	public static RoutineVO from(Routine routine, Theme theme) {
		return RoutineVO.builder()
			.routineId(routine.getId())
			.content(routine.getContent())
			.routineType(routine.getType())
			.theme(ThemeVO.from(theme))
			.build();
	}
}
