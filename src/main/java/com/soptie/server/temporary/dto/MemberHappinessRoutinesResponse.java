package com.soptie.server.temporary.dto;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.Mission;
import com.soptie.server.domain.membermission.MemberMission;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.temporary.data.ThemeData;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record MemberHappinessRoutinesResponse(
	long routineId,
	ThemeServiceResponse theme,
	String routineContent,
	String content,
	String description,
	String place,
	String requiredTime
) {

	public static MemberHappinessRoutinesResponse of(
		MemberMission memberMission,
		Challenge challenge,
		Mission mission,
		Theme theme
	) {
		return MemberHappinessRoutinesResponse.builder()
			.routineId(memberMission.getId())
			.theme(ThemeServiceResponse.of(theme))
			.routineContent(challenge.getName())
			.content(mission.getContent())
			.description(mission.getDescription())
			.place(mission.getPlace())
			.requiredTime(mission.getRequiredTime())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record ThemeServiceResponse(
		String iconImageUrl,
		String cardImageUrl,
		String name,
		String color
	) {

		private static ThemeServiceResponse of(Theme theme) {
			return ThemeServiceResponse.builder()
				.iconImageUrl(ThemeData.getIconImageUrl(theme.getId()))
				.cardImageUrl(ThemeData.getHappinessBackgroundUrl(theme.getId()))
				.name(theme.getName())
				.color(ThemeData.getColor(theme.getId()))
				.build();
		}
	}
}
