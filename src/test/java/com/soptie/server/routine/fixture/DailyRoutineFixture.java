package com.soptie.server.routine.fixture;

import java.util.ArrayList;
import java.util.List;

import com.soptie.server.routine.dto.DailyThemesResponse;
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
}
