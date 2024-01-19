package com.soptie.server.routine.repository.daily;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soptie.server.base.BaseRepositoryTest;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.entity.daily.RoutineImage;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;

class DailyThemeRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private DailyThemeRepository dailyThemeRepository;

	private final String THEME_NAME = "소중한 나";

	@DisplayName("데일리 루틴 테마 저장")
	@Test
	void success_createTheme() {
		// given
		DailyTheme theme = getTheme();

		// when
		DailyTheme result = dailyThemeRepository.save(theme);

		// then
		assertThat(result.getName(), is(equalTo(THEME_NAME)));
	}

	@DisplayName("데일리 루틴 테마 조회")
	@Test
	void success_getThemes_orderByNameAsc() {
		// given
		RoutineImage imageInfo = getImageInfo();
		DailyTheme savedTheme1 = dailyThemeRepository.save(new DailyTheme(THEME_NAME + 1, imageInfo));
		DailyTheme savedTheme2 = dailyThemeRepository.save(new DailyTheme(THEME_NAME + 2, imageInfo));
		List<DailyTheme> themes = new ArrayList<>(List.of(savedTheme1, savedTheme2));
		themes.sort(Comparator.comparing(DailyTheme::getName));

		// when
		List<DailyTheme> result = dailyThemeRepository.findAllOrderByNameAsc();

		// then
		assertThat(result.size(), is(equalTo(2)));
		assertThat(result, is(equalTo(themes)));
	}

	private DailyTheme getTheme() {
		return DailyTheme.builder()
				.name(THEME_NAME)
				.imageInfo(getImageInfo())
				.build();
	}

	private RoutineImage getImageInfo() {
		return RoutineImage.builder()
				.iconImageUrl("https://...")
				.backgroundImageUrl("https://...")
				.build();
	}

}