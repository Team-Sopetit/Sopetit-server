package com.soptie.server.routine.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.soptie.server.base.BaseServiceTest;
import com.soptie.server.routine.dto.DailyRoutinesResponse;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.entity.daily.RoutineImage;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class DailyRoutineServiceTest extends BaseServiceTest {

	@InjectMocks
	private DailyRoutineServiceImpl dailyRoutineService;

	@Mock
	private DailyRoutineRepository dailyRoutineRepository;
	@Mock
	private DailyThemeRepository dailyThemeRepository;

	private final DailyTheme THEME = theme();
	private final int LIST_SIZE = 5;
	private final String ROUTINE_CONTENT = "오늘의 음악 선곡하기";

	@DisplayName("테마별 데일리 루틴 조회")
	@Test
	void success_getRoutinesByTheme() {
		// given
		long themeId = 1L;

		when(dailyThemeRepository.findById(themeId)).thenReturn(Optional.of(THEME));
		when(dailyRoutineRepository.findAllByTheme(THEME)).thenReturn(routineList());

		// when
		DailyRoutinesResponse response = dailyRoutineService.getRoutinesByTheme(themeId);

		// then
		assertThat(response.routines().size(), is(equalTo(LIST_SIZE)));
		assertThat(response.routines().get(0).content(), is(equalTo(ROUTINE_CONTENT + 1)));

		// verify
		verify(dailyThemeRepository, times(1)).findById(themeId);
		verify(dailyRoutineRepository, times(1)).findAllByTheme(THEME);
	}

	private List<DailyRoutine> routineList() {
		return Stream.iterate(1, i -> i + 1).limit(LIST_SIZE)
				.map(this::routine).toList();
	}

	private DailyRoutine routine(int i) {
		return DailyRoutine.builder()
				.content(ROUTINE_CONTENT + i)
				.theme(THEME)
				.build();
	}

	private DailyTheme theme() {
		return DailyTheme.builder()
				.name("소중한 나")
				.imageInfo(imageInfo())
				.build();
	}

	private RoutineImage imageInfo() {
		return RoutineImage.builder()
				.iconImageUrl("https://...")
				.backgroundImageUrl("https://...")
				.build();
	}
}