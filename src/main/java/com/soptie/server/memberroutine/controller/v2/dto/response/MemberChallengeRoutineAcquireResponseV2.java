package com.soptie.server.memberroutine.controller.v2.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberroutine.service.dto.response.MemberChallengeRoutineAcquireServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MemberChallengeRoutineAcquireResponseV2(
	long routineId,
	long themeId,
	@NonNull String themeName,
	@NonNull String title,
	@NonNull String content,
	@NonNull String detailContent,
	@NonNull String place,
	@NonNull String timeTaken
) {

	public static MemberChallengeRoutineAcquireResponseV2 of(MemberChallengeRoutineAcquireServiceResponse response) {
		return MemberChallengeRoutineAcquireResponseV2.builder()
			.routineId(response.routineId())
			.themeId(response.theme().themeId())
			.themeName(response.theme().name())
			.title(response.routineContent())
			.content(response.content())
			.detailContent(response.description())
			.place(response.place())
			.timeTaken(response.requiredTime())
			.build();
	}
}
