package com.soptie.server.temporary.dto;

import java.util.List;
import java.util.Map;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.temporary.data.ThemeData;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record GetHappinessRoutinesResponse(
	List<HappinessRoutineResponse> routines
) {

	public static GetHappinessRoutinesResponse of(Map<Theme, List<Challenge>> map) {
		return GetHappinessRoutinesResponse.builder()
			.routines(map.entrySet().stream()
				.flatMap(entry -> entry.getValue().stream()
					.map(challenge -> HappinessRoutineResponse.of(entry.getKey(), challenge)))
				.toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	public record HappinessRoutineResponse(
		long routineId,
		@NonNull String name,
		@NonNull String nameColor,
		@NonNull String title,
		@NonNull String iconImageUrl
	) {

		public static HappinessRoutineResponse of(Theme theme, Challenge challenge) {
			return HappinessRoutineResponse.builder()
				.routineId(challenge.getId())
				.name(theme.getName())
				.nameColor(ThemeData.getColor(theme.getId()))
				.title(challenge.getName())
				.iconImageUrl(ThemeData.getIconImageUrl(theme.getId()))
				.build();
		}
	}
}
