package com.soptie.server.routine.service;

import com.soptie.server.routine.dto.HappinessThemesResponse;
import com.soptie.server.routine.entity.happiness.HappinessTheme;
import com.soptie.server.routine.repository.happiness.theme.HappinessThemeRepository;
import com.soptie.server.support.HappinessThemeFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class HappinessRoutineServiceImplTest {

    @InjectMocks
    private HappinessRoutineServiceImpl happinessRoutineService;

    @Mock
    private HappinessThemeRepository happinessThemeRepository;

    @Test
    @DisplayName("행복 루틴 테마 목록 조회 성공")
    void testGetHappinessThemes_success() {
        // given
        doReturn(happinessThemes()).when(happinessThemeRepository).findAllOrderByNameAsc();

        // when
        final HappinessThemesResponse actual = happinessRoutineService.getHappinessThemes();

        // then
        List<Long> themeIds = actual.themes().stream().map(HappinessThemesResponse.HappinessThemeResponse::themeId).toList();
        assertThat(themeIds).containsExactlyInAnyOrder(1L, 2L);
    }

    private List<HappinessTheme> happinessThemes() {
        return List.of(
                HappinessThemeFixture.happinessTheme().id(1L).name("theme1").build(),
                HappinessThemeFixture.happinessTheme().id(2L).name("theme2").build()
        );
    }
}
