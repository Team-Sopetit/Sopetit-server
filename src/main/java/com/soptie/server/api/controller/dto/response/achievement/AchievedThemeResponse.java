package com.soptie.server.api.controller.dto.response.achievement;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.MemberChallenge;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.val;

@Builder(access = AccessLevel.PRIVATE)
public record AchievedThemeResponse(
	@Schema(description = "테마 id", example = "1")
	long id,
	@NotNull
	@Schema(description = "테마 이름", example = "관계쌓기")
	String name,
	@NotNull
	@Schema(description = "루틴 목록")
	List<AchievedRoutine> routines,
	@NotNull
	@Schema(description = "챌린지 목록")
	List<AchievedChallenge> challenges
) {

	public static AchievedThemeResponse of(
		Theme theme,
		List<MemberRoutine> memberRoutines,
		Map<Long, Routine> routineMapOfMember,
		List<MemberChallenge> memberChallenges,
		Map<Long, Challenge> challengeMapOfMember
	) {
		return AchievedThemeResponse.builder()
			.id(theme.getId())
			.name(theme.getName())
			.routines(memberRoutines.stream()
				.map(it -> AchievedRoutine.of(routineMapOfMember.get(it.getId()), it))
				.sorted((a, b) -> {
					val diff = b.achievedCount - a.achievedCount;
					return diff != 0 ? diff : a.content.compareTo(b.content);
				})
				.toList())
			.challenges(memberChallenges.stream()
				.map(it -> AchievedChallenge.of(challengeMapOfMember.get(it.getId()), it))
				.sorted((a, b) -> {
					val diff = b.achievedCount - a.achievedCount;
					return diff != 0 ? diff : a.content.compareTo(b.content);
				})
				.toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record AchievedRoutine(
		@NotNull
		@Schema(description = "루틴 내용", example = "일찍 일어나기")
		String content,
		@Schema(description = "루틴 달성 횟수", example = "10")
		int achievedCount,
		@NotNull
		@Schema(description = "루틴 시작일", example = "2024-01-01")
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

	@Builder(access = AccessLevel.PRIVATE)
	private record AchievedChallenge(
		@NotNull
		@Schema(description = "챌린지 내용", example = "미라클 모닝")
		String content,
		@Schema(description = "챌린지 달성 횟수", example = "5")
		int achievedCount,
		@NotNull
		@Schema(description = "챌린지 시작일", example = "2024-01-01")
		LocalDate startedAt
	) {

		private static AchievedChallenge of(Challenge challenge, MemberChallenge memberChallenge) {
			return AchievedChallenge.builder()
				.content(challenge.getContent())
				.achievedCount(memberChallenge.getAchievedCount())
				.startedAt(memberChallenge.getCreatedAt())
				.build();
		}
	}
}
