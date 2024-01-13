package com.soptie.server.routine.fixture;

import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessThemesResponse;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.RoutineImage;

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
		RoutineImage routineImage = new RoutineImage(ICON_IMAGE_URL + i);
		return new HappinessThemesResponse.HappinessThemeResponse(NAME + i, NAME_COLOR + i, routineImage.getIconImageUrl());
	}

	public static HappinessRoutine createHappinessRoutine(int i) {
		HappinessThemesResponse.HappinessThemeResponse theme = createHappinessThemeResponse(i);
		return new HappinessRoutine((long)i, TITLE + i, theme);
	}

	public static HappinessRoutinesResponse createHappinessRoutinesResponseDTO() {
		List<HappinessRoutine> routines = createHappinessRoutines();
		return HappinessRoutinesResponse.of(routines);
	}

	private static List<HappinessRoutine> createHappinessRoutines() {
		return Stream.iterate(1, i -> i + 1).limit(5)
				.map(HappinessRoutineFixture::createHappinessRoutine).toList();
	}
}
