package com.soptie.server.routine.fixture;

import java.util.ArrayList;
import java.util.List;

import com.soptie.server.routine.dto.DailyRoutinesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.entity.daily.RoutineImage;

public class DailyRoutineFixture {

	private static final String NAME = "Daily routine Theme";

	private static final String ICON_IMAGE_URL = "icon_image_url";
	private static final String BACKGROUND_IMAGE_URL = "background_image_url";

	public static DailyThemesResponse createDailyThemesResponseDTO() {
		List<DailyTheme> themes = getDailyThemes();
		return DailyThemesResponse.of(themes);
	}

	private static List<DailyTheme> getDailyThemes() {
		List<DailyTheme> themes = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			themes.add(createDailyTheme(
				(long)i,
				NAME + i,
				ICON_IMAGE_URL + i,
				BACKGROUND_IMAGE_URL + i));
		}
		return themes;
	}

	private static DailyTheme createDailyTheme(Long id, String name, String iconImageUrl, String backgroundImageUrl) {
		RoutineImage routineImage = new RoutineImage(iconImageUrl, backgroundImageUrl);
		return new DailyTheme(id, name, routineImage);
	}

	public static DailyRoutinesResponse createDailyRoutinesResponseDTO() {
		List<DailyRoutine> routines = getDailyRoutines();
		return DailyRoutinesResponse.of(routines);
	}

	private static List<DailyRoutine> getDailyRoutines() {
		List<DailyRoutine> routines = new ArrayList<>();
		DailyTheme theme = createDailyTheme(1L, "테스트 테마", "test-icon", "test-background");
		for (int i = 0; i < 5; i++) {
			routines.add(createDailyRoutine((long)i, "content" + i, theme));
		}
		return routines;
	}

	private static DailyRoutine createDailyRoutine(Long id, String content, DailyTheme theme) {
		return new DailyRoutine(id, content, theme);
	}
}
