package com.soptie.server.routine.repository.daily.routine;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;
import com.soptie.server.support.DailyRoutineFixture;
import com.soptie.server.support.DailyThemeFixture;
import com.soptie.server.support.RepositoryTest;

@RepositoryTest
class DailyRoutineRepositoryTest {

	@Autowired
	DailyRoutineRepository dailyRoutineRepository;
	@Autowired
	DailyThemeRepository dailyThemeRepository;

	@DisplayName("테마별 데일리 루틴 전체 조회")
	@Test
	void findAllByTheme() {
		// given
		DailyTheme theme = DailyThemeFixture.dailyTheme().id(1L).name("daily theme").build();
		dailyThemeRepository.save(theme);

		dailyRoutineRepository.save(DailyRoutineFixture.dailyRoutine().id(1L).content("찬").theme(theme).build());
		dailyRoutineRepository.save(DailyRoutineFixture.dailyRoutine().id(2L).content("소현").theme(theme).build());
		dailyRoutineRepository.save(DailyRoutineFixture.dailyRoutine().id(3L).content("승빈").theme(theme).build());

		// when
		List<DailyRoutine> actual = dailyRoutineRepository.findAllByTheme(theme);

		// then
		assertThat(actual).hasSize(3);
		assertThat(actual.get(0).getContent()).isEqualTo("소현");
		assertThat(actual.get(1).getContent()).isEqualTo("승빈");
		assertThat(actual.get(2).getContent()).isEqualTo("찬");
	}
}