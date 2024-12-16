package com.soptie.server.api.controller.dto.response.challenge;

import java.util.List;

import com.soptie.server.domain.challenge.Challenge;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ChallengesResponse(
	@Schema(description = "챌린지 정보 목록")
	@NotNull List<ChallengeResponse> challenges
) {

	public static ChallengesResponse of(List<Challenge> challenges, List<Long> challengeIdsInMember) {
		return ChallengesResponse.builder()
			.challenges(challenges.stream()
				.map(it -> ChallengeResponse.of(it, challengeIdsInMember)).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record ChallengeResponse(
		@Schema(description = "챌린지 id", example = "1")
		long challengeId,
		@Schema(description = "챌린지 내용", example = "나의 소통 방식을 파악하기")
		@NotNull String content,
		@Schema(description = "챌린지 설명", example = "이상형의 특성을 생각해보고 ...더보기")
		@NotNull String description,
		@Schema(description = "소요 시간", example = "5~10분")
		@NotNull String requiredTime,
		@Schema(description = "장소", example = "퇴근길")
		@NotNull String place,
		@Schema(description = "추가된 여부", example = "true")
		boolean hasRoutine
	) {

		private static ChallengeResponse of(Challenge challenge, List<Long> challengeIdsInMember) {
			return ChallengeResponse.builder()
				.challengeId(challenge.getId())
				.content(challenge.getContent())
				.description(challenge.getDescription())
				.requiredTime(challenge.getRequiredTime())
				.place(challenge.getPlace())
				.hasRoutine(challengeIdsInMember.contains(challenge.getId()))
				.build();
		}
	}
}
