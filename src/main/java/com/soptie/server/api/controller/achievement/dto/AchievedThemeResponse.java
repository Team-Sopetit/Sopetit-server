package com.soptie.server.api.controller.achievement.dto;

import java.util.List;

import com.soptie.server.domain.achievement.AchievedChallenge;
import com.soptie.server.domain.achievement.AchievedRoutine;
import com.soptie.server.domain.achievement.Achievement;
import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AchievedThemeResponse(
	@Schema(description = "테마 id", example = "1")
	long id,

	@NotNull
	@Schema(description = "테마 이름", example = "관계쌓기")
	String name,

	@Schema(description = "전체 루틴 달성 횟수", example = "10")
	int routineTotalCount,

	@NotNull
	@Schema(description = "루틴 목록")
	List<AchievedRoutine> routines,

	@Schema(description = "전체 챌린지 달성 횟수", example = "10")
	int challengeTotalCount,

	@NotNull
	@Schema(description = "챌린지 목록")
	List<AchievedChallenge> challenges
) {

	public static AchievedThemeResponse from(Achievement achievement) {
		Theme theme = achievement.theme();
		List<AchievedRoutine> routines = achievement.achievedRoutines();
		List<AchievedChallenge> challenges = achievement.achievedChallenges();

		return AchievedThemeResponse.builder()
			.id(theme.getId())
			.name(theme.getName())
			.routineTotalCount(routines.stream().mapToInt(AchievedRoutine::achievedCount).sum())
			.routines(routines)
			.challengeTotalCount(challenges.stream().mapToInt(AchievedChallenge::achievedCount).sum())
			.challenges(challenges)
			.build();
	}
}
