package com.soptie.server.api.controller.dto.response.routine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soptie.server.domain.routine.Routine;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.val;

@Builder(access = AccessLevel.PRIVATE)
public record GetRoutinesByMemberResponse(
	@Schema(description = "조회하려는 루틴 목록")
	@NotNull List<RoutineResponse> routines
) {

	public static GetRoutinesByMemberResponse of(Map<Boolean, List<Routine>> routineToMember) {
		return GetRoutinesByMemberResponse.builder()
			.routines(ofRoutines(routineToMember))
			.build();
	}

	private static List<RoutineResponse> ofRoutines(Map<Boolean, List<Routine>> routineToMember) {
		val routines = new ArrayList<RoutineResponse>();
		for (val key : routineToMember.keySet()) {
			routines.addAll(routineToMember.get(key).stream()
				.map(routine -> RoutineResponse.of(routine, key))
				.toList());
		}
		return routines;
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record RoutineResponse(
		@Schema(description = "루틴 id", example = "1")
		long id,
		@Schema(description = "루틴 내용", example = "놀러가기")
		@NotNull String content,
		@Schema(description = "회원이 가지고 있는 루틴 유무", example = "true")
		boolean existedInMember
	) {

		private static RoutineResponse of(Routine routine, boolean existedInMember) {
			return RoutineResponse.builder()
				.id(routine.getId())
				.content(routine.getContent())
				.existedInMember(existedInMember)
				.build();
		}
	}
}
