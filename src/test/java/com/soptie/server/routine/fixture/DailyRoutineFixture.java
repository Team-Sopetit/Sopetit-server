package com.soptie.server.routine.fixture;

import java.util.List;
import java.util.stream.Stream;

import com.soptie.server.routine.dto.DailyRoutineResponse;
import com.soptie.server.routine.dto.DailyRoutinesByThemeResponse;
import com.soptie.server.routine.dto.DailyRoutinesByThemesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;

public class DailyRoutineFixture {

	public static DailyThemesResponse createDailyThemesResponseDTO() {
		return new DailyThemesResponse(createDailyThemeResponses());
	}

	private static List<DailyThemesResponse.DailyThemeResponse> createDailyThemeResponses() {
		return Stream.iterate(1, i -> i + 1).limit(5)
				.map(DailyRoutineFixture::createDailyThemeResponse).toList();
	}

	private static DailyThemesResponse.DailyThemeResponse createDailyThemeResponse(int i) {
		return DailyThemesResponse.DailyThemeResponse.builder()
				.themeId((long)i)
				.name("theme name" + i)
				.iconImageUrl("https://...")
				.backgroundImageUrl("https://...")
				.build();
	}

	public static DailyRoutinesByThemesResponse createDailyRoutinesByThemesResponseDTO() {
		return new DailyRoutinesByThemesResponse(createDailyRoutineResponses());
	}

	public static DailyRoutinesByThemeResponse createDailyRoutinesByThemeResponseDTO() {
		return new DailyRoutinesByThemeResponse("https://...", createDailyRoutineResponses());
	}

	private static List<DailyRoutineResponse> createDailyRoutineResponses() {
		return Stream.iterate(1, i -> i + 1).limit(5)
				.map(DailyRoutineFixture::createDailyRoutineResponse).toList();
	}

	private static DailyRoutineResponse createDailyRoutineResponse(int i) {
		return new DailyRoutineResponse((long)i, "routine content" + i);
	}
}
