package com.soptie.server.memberRoutine.controller.v1.dto.response;

import com.soptie.server.memberRoutine.service.dto.response.MemberHappinessRoutineGetServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record MemberHappinessRoutineGetResponse(
		long routineId,
		@NonNull String iconImageUrl,
		@NonNull String contentImageUrl,
		@NonNull String themeName,
		@NonNull String themeNameColor,
		@NonNull String title,
		@NonNull String content,
		@NonNull String detailContent,
		@NonNull String place,
		@NonNull String timeTaken
) {

	public static MemberHappinessRoutineGetResponse of(MemberHappinessRoutineGetServiceResponse response) {
		return MemberHappinessRoutineGetResponse.builder()
				.routineId(response.routineId())
				.iconImageUrl(response.theme().iconImageUrl())
				.contentImageUrl(response.theme().backgroundImageUrl())
				.themeName(response.theme().name())
				.themeNameColor(response.theme().color())
				.title(response.routineContent())
				.content(response.content())
				.detailContent(response.description())
				.place(response.place())
				.timeTaken(response.requiredTime())
				.build();
	}
}
