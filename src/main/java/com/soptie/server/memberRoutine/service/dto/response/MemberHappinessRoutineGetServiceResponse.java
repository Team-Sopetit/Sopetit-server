package com.soptie.server.memberRoutine.service.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberRoutine.repository.dto.MemberChallengeResponse;
import com.soptie.server.theme.entity.Theme;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberHappinessRoutineGetServiceResponse(
		long routineId,
		ThemeServiceResponse theme,
		String routineContent,
		String content,
		String description,
		String place,
		String requiredTime
) {

	public static MemberHappinessRoutineGetServiceResponse of(MemberChallengeResponse routine) {
		return MemberHappinessRoutineGetServiceResponse.builder()
				.routineId(routine.id())
				.theme(ThemeServiceResponse.of(routine.theme()))
				.routineContent(routine.routineContent())
				.content(routine.content())
				.description(routine.description())
				.place(routine.place())
				.requiredTime(routine.requiredTime())
				.build();
	}

	@Builder(access = PRIVATE)
	public record ThemeServiceResponse(
			String iconImageUrl,
			String backgroundImageUrl,
			String name,
			String color
	) {

		private static ThemeServiceResponse of(Theme theme) {
			return ThemeServiceResponse.builder()
					.iconImageUrl(theme.getImageInfo().getIconImageUrl())
					.backgroundImageUrl(theme.getImageInfo().getBackgroundImageUrl())
					.name(theme.getName())
					.color(theme.getColor())
					.build();
		}
	}
}
