package com.soptie.server.routine.service.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.service.vo.ChallengeVO;

import lombok.Builder;

@Builder(access = PRIVATE)
public record HappinessSubRoutineListGetServiceResponse(
	String routineContent, String themeName, String themeColor,
	String iconImageUrl, String backgroundImageUrl,
	List<HappinessSubRoutineServiceResponse> challenges
) {

	public static HappinessSubRoutineListGetServiceResponse of(Routine routine, List<ChallengeVO> challenges) {
		return HappinessSubRoutineListGetServiceResponse.builder()
			.routineContent(routine.getContent())
			.themeName(routine.getTheme().getName())
			.themeColor(routine.getTheme().getColor())
			.iconImageUrl(routine.getTheme().getImageLinks().getIconImageUrl())
			.backgroundImageUrl(routine.getTheme().getImageLinks().getHappinessCardImageUrl())
			.challenges(challenges.stream().map(HappinessSubRoutineServiceResponse::of).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record HappinessSubRoutineServiceResponse(
		long challengeId, String content, String description,
		String requiredTime, String place
	) {

		private static HappinessSubRoutineServiceResponse of(ChallengeVO challenge) {
			return HappinessSubRoutineServiceResponse.builder()
				.challengeId(challenge.challengeId())
				.content(challenge.content())
				.description(challenge.description())
				.requiredTime(challenge.requiredTime())
				.place(challenge.place())
				.build();
		}
	}
}
