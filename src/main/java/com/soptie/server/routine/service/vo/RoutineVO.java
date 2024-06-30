package com.soptie.server.routine.service.vo;

import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.RoutineType;
import com.soptie.server.theme.entity.Theme;
import com.soptie.server.theme.service.vo.ThemeVO;

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
