package com.soptie.server.routine.fixture;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.soptie.server.memberRoutine.fixture.MemberDailyRoutineFixture;
import com.soptie.server.routine.dto.DailyRoutinesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.entity.daily.RoutineImage;

public class DailyRoutineFixture {

	private static final String NAME = "Daily routine Theme";
	private static final String ICON_IMAGE_URL = "icon_image_url";
	private static final String BACKGROUND_IMAGE_URL = "background_image_url";
	private static final String CONTENT = "Daily routine Content";

	public static DailyThemesResponse createDailyThemesResponseDTO() {
		List<DailyTheme> themes = createDailyThemes();
		return DailyThemesResponse.of(themes);
	}

	public static DailyTheme createDailyTheme(int i) {
		RoutineImage routineImage = new RoutineImage(ICON_IMAGE_URL + i, BACKGROUND_IMAGE_URL + i);
		return new DailyTheme((long)i, NAME + i, routineImage);
	}

	public static DailyRoutine createDailyRoutine(int i) {
		DailyTheme theme = createDailyTheme(i);
		return new DailyRoutine((long)i, CONTENT + i, theme);
	}

	private static List<DailyTheme> createDailyThemes() {
		return Stream.iterate(1, i -> i + 1).limit(5)
				.map(DailyRoutineFixture::createDailyTheme).toList();
	}

	public static DailyRoutinesResponse createDailyRoutinesResponseDTO() {
		List<DailyRoutine> routines = createDailyRoutines();
		return DailyRoutinesResponse.of(routines);
	}

	private static List<DailyRoutine> createDailyRoutines() {
		return Stream.iterate(1, i -> i + 1).limit(5)
				.map(DailyRoutineFixture::createDailyRoutine).toList();
	}
}
