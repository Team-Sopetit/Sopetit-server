package com.soptie.server.routine.repository.daily;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;
import com.soptie.server.support.fixture.DailyRoutineFixture;
import com.soptie.server.support.fixture.DailyThemeFixture;
import com.soptie.server.support.RepositoryTest;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@RepositoryTest
class DailyRoutineRepositoryTest {

	@Autowired
	DailyRoutineRepository dailyRoutineRepository;
	@Autowired
	DailyThemeRepository dailyThemeRepository;

	@AfterEach
	void afterEach() {
		dailyRoutineRepository.deleteAll();
		dailyThemeRepository.deleteAll();
	}

	@Test
	void 테마_리스트에_포함된_모든_데일리_루틴을_내용_오름차순으로_조회한다() {
		// given
		DailyTheme savedTheme1 = dailyThemeRepository.save(DailyThemeFixture.dailyTheme().name("테마1").build());
		DailyTheme savedTheme2 = dailyThemeRepository.save(DailyThemeFixture.dailyTheme().name("테마2").build());
		DailyTheme savedTheme3 = dailyThemeRepository.save(DailyThemeFixture.dailyTheme().name("테마3").build());

		String firstGetContent = "가";
		String secondGetContent = "하하";
		dailyRoutineRepository.saveAll(List.of(
				DailyRoutineFixture.dailyRoutine().content(secondGetContent).theme(savedTheme1).build(),
				DailyRoutineFixture.dailyRoutine().content(firstGetContent).theme(savedTheme2).build(),
				DailyRoutineFixture.dailyRoutine().content("미포함 루틴").theme(savedTheme3).build()
		));

		// when
		List<Long> themeIds = List.of(
				savedTheme1.getId(),
				savedTheme2.getId()
		);
		List<DailyRoutine> actual = dailyRoutineRepository.findAllByThemes(themeIds);

		// then
		assertThat(actual).hasSize(2);
		assertThat(actual.get(0).getContent()).isEqualTo(firstGetContent);
		assertThat(actual.get(1).getContent()).isEqualTo(secondGetContent);
	}

	@Test
	void 테마에_포함된_모든_데일리_루틴을_내용_오름차순으로_조회한다() {
		// given
		DailyTheme savedTheme = dailyThemeRepository.save(DailyThemeFixture.dailyTheme().name("데일리 루틴 테마").build());

		String firstGetContent = "소현";
		String secondGetContent = "승빈";
		String thirdGetContent = "찬";
		dailyRoutineRepository.saveAll(List.of(
				DailyRoutineFixture.dailyRoutine().content(thirdGetContent).theme(savedTheme).build(),
				DailyRoutineFixture.dailyRoutine().content(firstGetContent).theme(savedTheme).build(),
				DailyRoutineFixture.dailyRoutine().content(secondGetContent).theme(savedTheme).build()
		));

		// when
		List<DailyRoutine> actual = dailyRoutineRepository.findAllByTheme(savedTheme);

		// then
		assertThat(actual).hasSize(3);
		assertThat(actual.get(0).getContent()).isEqualTo(firstGetContent);
		assertThat(actual.get(1).getContent()).isEqualTo(secondGetContent);
		assertThat(actual.get(2).getContent()).isEqualTo(thirdGetContent);
	}
}