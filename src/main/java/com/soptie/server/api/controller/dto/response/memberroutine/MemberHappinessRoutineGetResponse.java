package com.soptie.server.api.controller.dto.response.memberroutine;

import static lombok.AccessLevel.*;

import com.soptie.server.domain.memberroutine.MemberHappinessRoutineGetServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
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
			.contentImageUrl(response.theme().cardImageUrl())
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
