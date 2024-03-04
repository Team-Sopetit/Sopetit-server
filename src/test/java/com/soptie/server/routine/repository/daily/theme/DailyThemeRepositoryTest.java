package com.soptie.server.routine.repository.daily.theme;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.support.DailyThemeFixture;
import com.soptie.server.support.RepositoryTest;

@RepositoryTest
class DailyThemeRepositoryTest {

	@Autowired
	DailyThemeRepository dailyThemeRepository;

	@Test
	void findAllThemes_success() {
		// given
		String imageUrl = "https://www...";
		dailyThemeRepository.save(DailyThemeFixture.dailyTheme().id(1L).name("소현 서버").imageUrl(imageUrl).build());
		dailyThemeRepository.save(DailyThemeFixture.dailyTheme().id(2L).name("찬 서버").imageUrl(imageUrl).build());
		dailyThemeRepository.save(DailyThemeFixture.dailyTheme().id(3L).name("승빈 서버").imageUrl(imageUrl).build());

		// when
		List<DailyTheme> actual = dailyThemeRepository.findAllOrderByNameAsc();

		// then
		assertThat(actual).hasSize(3);
		assertThat(actual.get(0).getName()).isEqualTo("소현 서버");
		assertThat(actual.get(1).getName()).isEqualTo("승빈 서버");
		assertThat(actual.get(2).getName()).isEqualTo("찬 서버");
	}
}