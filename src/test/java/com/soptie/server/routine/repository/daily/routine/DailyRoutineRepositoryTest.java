package com.soptie.server.routine.repository.daily.routine;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soptie.server.base.BaseRepositoryTest;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.entity.daily.RoutineImage;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

class DailyRoutineRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private DailyRoutineRepository dailyRoutineRepository;
	@Autowired
	private DailyThemeRepository dailyThemeRepository;

	private final String ROUTINE_CONTENT = "오늘의 음악 선곡하기";
	private final DailyTheme THEME = getTheme();

	@BeforeEach
	void init() {
		dailyThemeRepository.save(THEME);
	}

	@DisplayName("데일리 루틴 저장")
	@Test
	void success_createRoutine() {
		// given
		int i = 0;
		DailyRoutine routine = getRoutine(i);

		// when
		DailyRoutine savedRoutine = dailyRoutineRepository.save(routine);

		// then
		assertThat(savedRoutine.getContent(), is(equalTo(ROUTINE_CONTENT + i)));
		assertThat(savedRoutine.getTheme(), is(equalTo(THEME)));
	}

	@DisplayName("데일리 루틴 조회")
	@Test
	void success_getRoutine() {
		// given
		int i = 0;
		DailyRoutine savedRoutine = dailyRoutineRepository.save(getRoutine(i));

		// when
		Optional<DailyRoutine> routine = dailyRoutineRepository.findById(savedRoutine.getId());

		// then
		assertThat(routine.isPresent(), is(equalTo(true)));
		assertThat(routine.get(), is(equalTo(savedRoutine)));
		assertThat(routine.get().getId(), is(equalTo(savedRoutine.getId())));
		assertThat(routine.get().getContent(), is(equalTo(savedRoutine.getContent())));
		assertThat(routine.get().getTheme(), is(equalTo(savedRoutine.getTheme())));
	}

	@DisplayName("테마별 데일리 루틴 목록 조회")
	@Test
	void success_getRoutinesByTheme() {
		// given
		int size = 5;
		Stream.iterate(1, i -> i + 1).limit(size)
				.forEach(i -> dailyRoutineRepository.save(getRoutine(i)));

		// when
		List<DailyRoutine> result = dailyRoutineRepository.findAllByTheme(THEME);

		// then
		assertThat(result.size(), is(equalTo(size)));
	}

	private DailyRoutine getRoutine(int i) {
		return DailyRoutine.builder()
				.content(ROUTINE_CONTENT + i)
				.theme(THEME)
				.build();
	}

	private DailyTheme getTheme() {
		return DailyTheme.builder()
				.name("소중한 나")
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