package com.soptie.server.temporary.dto;

import java.util.List;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.Mission;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.temporary.data.ThemeData;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record GetHappinessSubRoutinesResponse(
	@NonNull String title,
	@NonNull String name,
	@NonNull String nameColor,
	@NonNull String iconImageUrl,
	@NonNull String contentImageUrl,
	@NonNull List<HappinessSubRoutineResponse> subRoutines
) {

	public static GetHappinessSubRoutinesResponse of(Theme theme, Challenge challenge, List<Mission> missions) {
		return GetHappinessSubRoutinesResponse.builder()
			.title(challenge.getName())
			.name(theme.getName())
			.nameColor(ThemeData.getColor(theme.getId()))
			.iconImageUrl(ThemeData.getIconImageUrl(theme.getId()))
			.contentImageUrl(ThemeData.getHappinessBackgroundUrl(theme.getId()))
			.subRoutines(missions.stream().map(HappinessSubRoutineResponse::of).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	public record HappinessSubRoutineResponse(
		long subRoutineId,
		@NonNull String content,
		@NonNull String detailContent,
		@NonNull String timeTaken,
		@NonNull String place
	) {

		public static HappinessSubRoutineResponse of(Mission mission) {
			return HappinessSubRoutineResponse.builder()
				.subRoutineId(mission.getId())
				.content(mission.getContent())
				.detailContent(mission.getDescription())
				.timeTaken(mission.getRequiredTime())
				.place(mission.getPlace())
				.build();
		}
	}
}
