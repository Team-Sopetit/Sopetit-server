package com.soptie.server.routine.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.routine.dto.DailyThemesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse.DailyThemeResponse;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;
import com.soptie.server.support.DailyThemeFixture;

@ExtendWith(MockitoExtension.class)
class DailyRoutineServiceImplTest {

	@InjectMocks
	private DailyRoutineServiceImpl dailyRoutineService;

	@Mock
	private DailyThemeRepository dailyThemeRepository;

	@DisplayName("데일리 루틴 테마 목록 조회 성공")
	@Test
	void getAllThemes_success() {
		// given
		doReturn(dailyThemes()).when(dailyThemeRepository).findAllOrderByNameAsc();

		// when
		final DailyThemesResponse actual = dailyRoutineService.getThemes();

		// then
		List<Long> themeIds = actual.themes().stream().map(DailyThemeResponse::themeId).toList();
		assertThat(themeIds).containsExactlyInAnyOrder(1L, 2L);
	}

	private List<DailyTheme> dailyThemes() {
		String imageUrl = "https://www...";
		return List.of(
				DailyThemeFixture.dailyTheme().id(1L).name("theme1").imageUrl(imageUrl).build(),
				DailyThemeFixture.dailyTheme().id(2L).name("theme2").imageUrl(imageUrl).build()
		);
	}
}