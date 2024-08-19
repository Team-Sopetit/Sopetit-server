package com.soptie.server.domain.memberroutine;

import static lombok.AccessLevel.*;

import com.soptie.server.persistence.repository.dto.MemberChallengeResponse;
import com.soptie.server.persistence.entity.Theme;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberChallengeRoutineAcquireServiceResponse(
	long routineId,
	ThemeServiceResponse theme,
	String routineContent,
	String content,
	String description,
	String place,
	String requiredTime
) {

	public static MemberChallengeRoutineAcquireServiceResponse of(MemberChallengeResponse routine) {
		return MemberChallengeRoutineAcquireServiceResponse.builder()
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
		long themeId,
		String name
	) {

		private static ThemeServiceResponse of(Theme theme) {
			return ThemeServiceResponse.builder()
				.themeId(theme.getId())
				.name(theme.getName())
				.build();
		}
	}
}
