package com.soptie.server.theme.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soptie.server.support.RepositoryTest;
import com.soptie.server.support.fixture.ThemeFixture;
import com.soptie.server.theme.entity.Theme;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@RepositoryTest
class ThemeRepositoryTest {

	@Autowired
	ThemeRepository themeRepository;

	@AfterEach
	void afterEach() {
		themeRepository.deleteAllInBatch();
	}

	@Test
	@DisplayName("[성공] 모든 테마 정보를 이름 오름차순으로 조회한다.")
	void findAllThemesOrderByNameAsc() {
		// given
		String firstGetName = "관계 쌓기";
		String secondGetName = "새로운 나";
		String thirdGetName = "한 걸음 성장";

		themeRepository.saveAll(List.of(
				ThemeFixture.theme().name(firstGetName).build(),
				ThemeFixture.theme().name(thirdGetName).build(),
				ThemeFixture.theme().name(secondGetName).build()
		));

		// when
		List<Theme> actual = themeRepository.findAllOrderByNameAsc();

		// then
		assertThat(actual).hasSize(3);
		assertThat(actual.get(0).getName()).isEqualTo(firstGetName);
		assertThat(actual.get(1).getName()).isEqualTo(secondGetName);
		assertThat(actual.get(2).getName()).isEqualTo(thirdGetName);
	}
}