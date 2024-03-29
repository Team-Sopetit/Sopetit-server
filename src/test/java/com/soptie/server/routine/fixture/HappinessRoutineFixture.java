package com.soptie.server.routine.fixture;

import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessThemesResponse;

import java.util.List;
import java.util.stream.Stream;

public class HappinessRoutineFixture {

	private static final String NAME = "Happiness routine Theme";
	private static final String NAME_COLOR = "Happiness routine Theme";
	private static final String TITLE = "Happiness routine Theme";
	private static final String ICON_IMAGE_URL = "icon_image_url";

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

	public static HappinessRoutinesResponse createHappinessRoutinesResponseDTO() {
		List<HappinessRoutinesResponse.HappinessRoutineResponse> routines = createHappinessRoutineResponses();
		return new HappinessRoutinesResponse(routines);
	}

	private static List<HappinessRoutinesResponse.HappinessRoutineResponse> createHappinessRoutineResponses() {
		return Stream.iterate(1, i -> i + 1).limit(5)
				.map(HappinessRoutineFixture::createHappinessRoutineResponse).toList();
	}

	private static HappinessRoutinesResponse.HappinessRoutineResponse createHappinessRoutineResponse(int i) {
		return new HappinessRoutinesResponse.HappinessRoutineResponse((long)i, NAME + i, NAME_COLOR + i, TITLE + i, ICON_IMAGE_URL + i);
	}
}
