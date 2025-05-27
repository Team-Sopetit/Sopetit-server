package com.soptie.server.domain.achievement;

import java.util.List;

import com.soptie.server.domain.theme.Theme;

import lombok.Builder;

@Builder
public record Achievement(
	Theme theme,
	List<AchievedRoutine> achievedRoutines,
	List<AchievedChallenge> achievedChallenges
) {

	public static Achievement of(
		Theme theme,
		List<AchievedRoutine> achievedRoutines,
		List<AchievedChallenge> achievedChallenge
	) {
		return Achievement.builder()
			.theme(theme)
			.achievedRoutines(achievedRoutines)
			.achievedChallenges(achievedChallenge)
			.build();
	}
}
