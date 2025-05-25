package com.soptie.server.api.controller.memberroutine.dto;

import com.soptie.server.domain.memberroutine.MemberRoutine;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AchieveMemberRoutineResponse(
	@Schema(description = "달성 업데이트한 회원의 루틴 id", example = "1")
	long routineId,
	@Schema(description = "달성 여부", example = "true")
	boolean isAchieve,
	@Schema(description = "달성 횟수", example = "10")
	int achieveCount,
	@Schema(description = "솜뭉치 획득 여부", example = "true")
	boolean hasCotton
) {

	public static AchieveMemberRoutineResponse of(MemberRoutine memberRoutine, boolean isAcquiredCotton) {
		return AchieveMemberRoutineResponse.builder()
			.routineId(memberRoutine.getRoutineId())
			.isAchieve(memberRoutine.isAchieved())
			.achieveCount(memberRoutine.getAchievementCount())
			.hasCotton(isAcquiredCotton)
			.build();
	}
}
