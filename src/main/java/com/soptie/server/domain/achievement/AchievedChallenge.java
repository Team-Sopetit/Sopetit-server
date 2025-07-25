package com.soptie.server.domain.achievement;

import java.time.LocalDate;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.MemberChallenge;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AchievedChallenge(
	@NotNull
	@Schema(description = "챌린지 내용", example = "미라클 모닝")
	String content,

	@Schema(description = "챌린지 달성 횟수", example = "5")
	int achievedCount,

	@NotNull
	@Schema(description = "챌린지 시작일", example = "2024-01-01")
	LocalDate startedAt
) {

	public static AchievedChallenge of(MemberChallenge memberChallenge, Challenge challenge) {
		return AchievedChallenge.builder()
			.content(challenge.getContent())
			.achievedCount(memberChallenge.getAchievedCount())
			.startedAt(memberChallenge.getCreatedAt())
			.build();
	}
}
