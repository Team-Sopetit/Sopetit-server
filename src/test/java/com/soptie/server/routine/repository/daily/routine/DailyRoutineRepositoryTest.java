package com.soptie.server.routine.repository.daily.routine;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

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

	@Test
	void 테마_리스트로_조회하면_테마_리스트에_포함된_모든_루틴들을_조회한다() {
		// given
		List<Long> themeIds = List.of(1L, 2L);
		DailyTheme theme1 = DailyThemeFixture.dailyTheme().id(themeIds.get(0)).name("daily theme").build();
		DailyTheme theme2 = DailyThemeFixture.dailyTheme().id(themeIds.get(1)).name("daily theme").build();
		DailyTheme theme3 = DailyThemeFixture.dailyTheme().id(3L).name("daily theme").build();
		dailyThemeRepository.save(theme1);
		dailyThemeRepository.save(theme2);
		dailyThemeRepository.save(theme3);

		dailyRoutineRepository.save(DailyRoutineFixture.dailyRoutine().id(1L).content("하하").theme(theme1).build());
		dailyRoutineRepository.save(DailyRoutineFixture.dailyRoutine().id(2L).content("가").theme(theme2).build());
		dailyRoutineRepository.save(DailyRoutineFixture.dailyRoutine().id(3L).content("미포함 루틴").theme(theme3).build());

		// when
		List<DailyRoutine> actual = dailyRoutineRepository.findAllByThemes(themeIds);

		// then
		assertThat(actual).hasSize(2);
		assertThat(actual.get(0).getContent()).isEqualTo("가");
		assertThat(actual.get(1).getContent()).isEqualTo("하하");
	}

	@Test
	void 테마로_조회하면_테마에_포함된_모든_데일리루틴이_조회된다() {
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