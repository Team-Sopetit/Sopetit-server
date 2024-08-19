package com.soptie.server.domain.theme;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.entity.deleted.Theme;
import com.soptie.server.persistence.entity.deleted.ThemeType;
import com.soptie.server.persistence.repository.ThemeRepository;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.ThemeFixture;

@IntegrationTest
@Transactional
public class ThemeServiceIntegrationTest {

	@Autowired
	ThemeService themeService;

	@Autowired
	ThemeRepository themeRepository;

	@Nested
	class Acquire {

		List<Theme> themes = List.of(
			ThemeFixture.theme().type(ThemeType.BASIC).build(),
			ThemeFixture.theme().type(ThemeType.BASIC).build(),
			ThemeFixture.theme().type(ThemeType.BASIC).build(),
			ThemeFixture.theme().type(ThemeType.MAKER).build(),
			ThemeFixture.theme().type(ThemeType.MAKER).build()
		);

		@BeforeEach
		void setUp() {
			themeRepository.saveAll(themes);
		}

		@Test
		@DisplayName("[성공] BASIC 타입의 모든 테마 목록을 조회한다.")
		void acquireAllInBasic() {
			// given
			long countOfBasic = themes.stream().filter(theme -> theme.getType().equals(ThemeType.BASIC)).count();

			// when
			List<ThemeVO> result = themeService.acquireAllInBasic();

			// then
			assertThat(result).hasSize((int)countOfBasic);
		}
	}
}
