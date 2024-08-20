package com.soptie.server.api.controller.dto.response.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.Mission;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.val;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengesByMemberResponse(
	@Schema(description = "도전루틴 정보 목록")
	@NotNull List<ChallengeResponse> routines
) {

	public static GetChallengesByMemberResponse of(Map<Challenge, Map<Boolean, List<Mission>>> challengeByMember) {
		return GetChallengesByMemberResponse.builder()
			.routines(toChallenges(challengeByMember))
			.build();
	}

	private static List<ChallengeResponse> toChallenges(
		Map<Challenge, Map<Boolean, List<Mission>>> challengeByMember
	) {
		return challengeByMember.entrySet().stream()
			.map(entry -> ChallengeResponse.of(entry.getKey(), entry.getValue()))
			.toList();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record ChallengeResponse(
		@Schema(description = "도전루틴 제목", example = "오래가는 인연을 위한 준비")
		@NotNull String title,
		@Schema(description = "미션 정보 목록")
		@NotNull List<MissionResponse> challenges
	) {

		private static ChallengeResponse of(Challenge challenge, Map<Boolean, List<Mission>> missionsByMember) {
			return ChallengeResponse.builder()
				.title(challenge.getName())
				.challenges(ofMissions(missionsByMember))
				.build();
		}

		private static List<MissionResponse> ofMissions(Map<Boolean, List<Mission>> missionsByMember) {
			val missions = new ArrayList<MissionResponse>();
			for (val key : missionsByMember.keySet()) {
				missions.addAll(missionsByMember.get(key).stream()
					.map(mission -> MissionResponse.of(mission, key))
					.toList());
			}
			return missions;
		}
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record MissionResponse(
		@Schema(description = "미션(도전루틴) id", example = "1")
		long challengeId,
		@Schema(description = "미션 내용", example = "나의 소통 방식을 파악하기")
		@NotNull String content,
		@Schema(description = "미션 설명", example = "이상형의 특성을 생각해보고 ...더보기")
		@NotNull String description,
		@Schema(description = "소요 시간", example = "5~10분")
		@NotNull String requiredTime,
		@Schema(description = "장소", example = "퇴근길")
		@NotNull String place,
		@Schema(description = "미션 추가 여부", example = "true")
		boolean hasRoutine
	) {

		private static MissionResponse of(Mission mission, boolean existedInMember) {
			return MissionResponse.builder()
				.challengeId(mission.getId())
				.content(mission.getContent())
				.description(mission.getDescription())
				.requiredTime(mission.getRequiredTime())
				.place(mission.getPlace())
				.hasRoutine(existedInMember)
				.build();
		}
	}
}
