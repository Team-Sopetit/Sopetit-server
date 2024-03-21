package com.soptie.server.routine.repository.daily.theme;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.support.DailyThemeFixture;
import com.soptie.server.support.RepositoryTest;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@RepositoryTest
class DailyThemeRepositoryTest {

	@Autowired
	DailyThemeRepository dailyThemeRepository;

	@AfterEach
	void afterEach() {
		dailyThemeRepository.deleteAllInBatch();
	}

	@Test
	void 모든_데일리_루틴의_테마를_이름_오름차순으로_조회한다() {
		// given
		String firstGetName = "소현 테마";
		String secondGetName = "승빈 테마";
		String thirdGetName = "찬 테마";
		dailyThemeRepository.saveAll(List.of(
				DailyThemeFixture.dailyTheme().name(firstGetName).imageUrl("image-url1").build(),
				DailyThemeFixture.dailyTheme().name(thirdGetName).imageUrl("image-url2").build(),
				DailyThemeFixture.dailyTheme().name(secondGetName).imageUrl("image-url3").build()
		));

		// when
		List<DailyTheme> actual = dailyThemeRepository.findAllOrderByNameAsc();

		// then
		assertThat(actual).hasSize(3);
		assertThat(actual.get(0).getName()).isEqualTo(firstGetName);
		assertThat(actual.get(1).getName()).isEqualTo(secondGetName);
		assertThat(actual.get(2).getName()).isEqualTo(thirdGetName);
	}
}