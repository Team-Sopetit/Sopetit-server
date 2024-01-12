package com.soptie.server.routine.fixture;

import com.soptie.server.routine.dto.HappinessThemesResponse;

import java.util.List;
import java.util.stream.Stream;

public class HappinessRoutineFixture {

	private static final String NAME = "Happiness routine Theme";

	public static HappinessThemesResponse createHappinessThemesResponseDTO() {
		List<HappinessThemesResponse.HappinessThemeResponse> routines = createHappinessThemeResponses();
		return new HappinessThemesResponse(routines);
	}

	private static List<HappinessThemesResponse.HappinessThemeResponse> createHappinessThemeResponses() {
		return Stream.iterate(1, i -> i + 1).limit(5)
				.map(HappinessRoutineFixture::createHappinessThemeResponse).toList();
	}

	private static HappinessThemesResponse.HappinessThemeResponse createHappinessThemeResponse(int i) {
		return new HappinessThemesResponse.HappinessThemeResponse((long)i, NAME + i);
	}
}
