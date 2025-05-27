package com.soptie.server.domain.achievement;

import java.time.LocalDate;

import com.soptie.server.domain.memberroutine.MemberRoutine;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AchievedRoutine(
	@NotNull
	@Schema(description = "루틴 내용", example = "일찍 일어나기")
	String content,

	@Schema(description = "루틴 달성 횟수", example = "10")
	int achievedCount,

	@NotNull
	@Schema(description = "루틴 시작일", example = "2024-01-01")
	LocalDate startedAt
) {

	public static AchievedRoutine from(MemberRoutine memberRoutine) {
		return AchievedRoutine.builder()
			.content(memberRoutine.getContent())
			.achievedCount(memberRoutine.getAchievementCount())
			.startedAt(memberRoutine.getCreatedAt())
			.build();
	}
}
