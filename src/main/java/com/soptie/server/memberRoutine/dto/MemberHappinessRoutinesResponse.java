package com.soptie.server.memberRoutine.dto;

import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;

import lombok.Builder;

@Builder
public record MemberHappinessRoutinesResponse(
		Long routineId,
		String iconImageUrl,
		String contentImageUrl,
		String themeName,
		String themeNameColor,
		String title,
		String content,
		String detailContent,
		String place,
		String timeTaken
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
