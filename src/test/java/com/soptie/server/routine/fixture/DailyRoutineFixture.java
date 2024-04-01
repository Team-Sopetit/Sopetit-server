package com.soptie.server.routine.fixture;

import java.util.List;
import java.util.stream.Stream;

import com.soptie.server.routine.dto.daily.DailyRoutinesByThemeGetResponse;
import com.soptie.server.routine.dto.daily.DailyRoutinesByThemesGetResponse;
import com.soptie.server.routine.dto.daily.DailyThemesGetResponse;

public class DailyRoutineFixture {

	public static DailyThemesGetResponse createDailyThemesResponseDTO() {
		return new DailyThemesGetResponse(createDailyThemeResponses());
	}

	private static List<DailyThemesGetResponse.DailyThemeResponse> createDailyThemeResponses() {
		return Stream.iterate(1, i -> i + 1).limit(5)
				.map(DailyRoutineFixture::createDailyThemeResponse).toList();
	}

	private static DailyThemesGetResponse.DailyThemeResponse createDailyThemeResponse(int i) {
		return DailyThemesGetResponse.DailyThemeResponse.builder()
				.themeId(i)
				.name("theme name" + i)
				.iconImageUrl("https://...")
				.backgroundImageUrl("https://...")
				.build();
	}

	public static DailyRoutinesByThemesGetResponse createDailyRoutinesByThemesResponseDTO() {
		List<DailyRoutinesByThemesGetResponse.DailyRoutineResponse> routines = Stream.iterate(1, i -> i + 1)
				.limit(5)
				.map(i -> new DailyRoutinesByThemesGetResponse.DailyRoutineResponse((long)i, "routine content" + i))
				.toList();
		return new DailyRoutinesByThemesGetResponse(routines);
	}

	public static DailyRoutinesByThemeGetResponse createDailyRoutinesByThemeResponseDTO() {
		List<DailyRoutinesByThemeGetResponse.DailyRoutineResponse> routines = Stream.iterate(1, i -> i + 1)
				.limit(5)
				.map(i -> new DailyRoutinesByThemeGetResponse.DailyRoutineResponse((long)i, "routine content" + i))
				.toList();
		return new DailyRoutinesByThemeGetResponse("https://...", routines);
	}
}
