package com.soptie.server.api.controller.dto.response.routine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.val;

@Builder(access = AccessLevel.PRIVATE)
public record DailyRoutinesAcquireResponseV2(
	@Schema(description = "조회하려는 루틴 목록")
	@NotNull
	List<DailyRoutineResponse> routines
) {

	public static DailyRoutinesAcquireResponseV2 from(Map<Boolean, List<Routine>> routineToMember) {
		val routines = new ArrayList<DailyRoutineResponse>();
		for (val key : routineToMember.keySet()) {
			routines.addAll(routineToMember.get(key).stream()
				.map(routine -> DailyRoutineResponse.of(routine, key))
				.toList());
		}
		return DailyRoutinesAcquireResponseV2.builder()
			.routines(routines)
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record DailyRoutineResponse(
		@Schema(description = "루틴 id")
		long id,
		@Schema(description = "루틴 내용")
		@NotNull
		String content,
		@Schema(description = "회원이 가지고 있는 루틴 유무")
		boolean existedInMember
	) {

		private static DailyRoutineResponse of(Routine routine, boolean existedInMember) {
			return DailyRoutineResponse.builder()
				.id(routine.getId())
				.content(routine.getContent())
				.existedInMember(existedInMember)
				.build();
		}
	}
}
