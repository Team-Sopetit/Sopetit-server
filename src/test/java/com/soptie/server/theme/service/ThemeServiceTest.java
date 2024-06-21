package com.soptie.server.theme.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.support.fixture.ThemeFixture;
import com.soptie.server.theme.adapter.ThemeFinder;
import com.soptie.server.theme.entity.Theme;
import com.soptie.server.theme.service.dto.response.ThemeListGetServiceResponse;

@ExtendWith(MockitoExtension.class)
class ThemeServiceTest {

	@InjectMocks
	private ThemeService themeService;

	@Mock
	private ThemeFinder themeFinder;

	@Test
	@DisplayName("[성공] 모든 테마 정보를 조회한다.")
	void getAllThemes() {
		// given
		List<Theme> themes = List.of(
			ThemeFixture.theme().id(1L).build(),
			ThemeFixture.theme().id(2L).build()
		);

		doReturn(themes).when(themeFinder).findAllOrderByNameAsc();

		// when
		final ThemeListGetServiceResponse actual = themeService.getThemes();

		// then
		List<Long> themeIds = actual.themes().stream()
			.map(ThemeListGetServiceResponse.ThemeServiceResponse::themeId)
			.toList();
		assertThat(themeIds).containsExactlyInAnyOrder(1L, 2L);
	}
}
