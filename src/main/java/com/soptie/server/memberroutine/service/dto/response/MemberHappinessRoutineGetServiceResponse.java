package com.soptie.server.memberroutine.service.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberroutine.repository.dto.MemberChallengeResponse;
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
		String cardImageUrl,
		String name,
		String color
	) {

		private static ThemeServiceResponse of(Theme theme) {
			return ThemeServiceResponse.builder()
				.iconImageUrl(theme.getImageLinks().getIconImageUrl())
				.cardImageUrl(theme.getImageLinks().getHappinessCardImageUrl())
				.name(theme.getName())
				.color(theme.getColor())
				.build();
		}
	}
}
