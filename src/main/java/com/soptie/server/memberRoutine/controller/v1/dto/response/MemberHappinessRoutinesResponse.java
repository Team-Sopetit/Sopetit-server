package com.soptie.server.memberRoutine.controller.v1.dto.response;

import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record MemberHappinessRoutinesResponse(
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

	public static MemberHappinessRoutinesResponse of(MemberHappinessRoutine routine) {
		return MemberHappinessRoutinesResponse.builder()
				.routineId(routine.getId())
				.iconImageUrl(routine.getRoutine().getRoutine().getTheme().getImageInfo().getIconImageUrl())
				.contentImageUrl(routine.getRoutine().getRoutine().getTheme().getImageInfo().getContentImageUrl())
				.themeName(routine.getRoutine().getRoutine().getTheme().getName())
				.themeNameColor(routine.getRoutine().getRoutine().getTheme().getNameColor())
				.title(routine.getRoutine().getRoutine().getTitle())
				.content(routine.getRoutine().getContent())
				.detailContent(routine.getRoutine().getDetailContent())
				.place(routine.getRoutine().getPlace())
				.timeTaken(routine.getRoutine().getTimeTaken())
				.build();
	}
}
