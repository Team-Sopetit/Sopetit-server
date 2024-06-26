package com.soptie.server.routine.service.vo;

import static lombok.AccessLevel.*;

import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.RoutineType;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record RoutineVO(
	long routineId,
	@NotNull String content,
	@NotNull RoutineType routineType
) {

	public static RoutineVO from(Routine routine) {
		return RoutineVO.builder()
			.routineId(routine.getId())
			.content(routine.getContent())
			.routineType(routine.getType())
			.build();
	}
}
