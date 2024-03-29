package com.soptie.server.routine.service;

import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessThemesResponse;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessTheme;
import com.soptie.server.routine.entity.happiness.RoutineImage;
import com.soptie.server.routine.repository.happiness.routine.HappinessRoutineRepository;
import com.soptie.server.routine.repository.happiness.theme.HappinessThemeRepository;
import com.soptie.server.support.HappinessThemeFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class HappinessRoutineServiceImplTest {

    @InjectMocks
    private HappinessRoutineServiceImpl happinessRoutineService;

    @Mock
    private HappinessThemeRepository happinessThemeRepository;

    @Mock
    private HappinessRoutineRepository happinessRoutineRepository;

    @Test
    @DisplayName("행복 루틴 테마 목록 조회 성공")
    void 행복_루틴_테마_목록을_조회한다() {
        // given
        doReturn(happinessThemes()).when(happinessThemeRepository).findAllOrderByNameAsc();

        // when
        final HappinessThemesResponse actual = happinessRoutineService.getHappinessThemes();

        // then
        List<Long> themeIds = actual.themes().stream().map(HappinessThemesResponse.HappinessThemeResponse::themeId).toList();
        assertThat(themeIds).containsExactly(1L, 2L);

    }

    @Test
    void 주어진_테마에_속하는_행복_루틴_목록을_조회합니다() {
        // given
        long themeId = 1L;
        RoutineImage imageInfo = new RoutineImage("http://example.com/icon.jpg", "http://example.com/content.jpg", "http://example.com/twinkle.jpg");
        HappinessTheme theme = new HappinessTheme("사랑", "빨간색", imageInfo);

        HappinessRoutine routine1 = new HappinessRoutine(1L, "루틴1", theme);
        HappinessRoutine routine2 = new HappinessRoutine(2L, "루틴2", theme);
        List<HappinessRoutine> routines = Arrays.asList(routine1, routine2);

        doReturn(routines).when(happinessRoutineRepository).findAllByThemeId(themeId);

        // when
        HappinessRoutinesResponse response = happinessRoutineService.getHappinessRoutinesByTheme(themeId);

        // then
        assertThat(response).isNotNull();
        assertThat(response.routines()).hasSize(2);
        assertThat(response.routines().get(0).routineId()).isEqualTo(1L);
        assertThat(response.routines().get(1).routineId()).isEqualTo(2L);
    }


    private List<HappinessTheme> happinessThemes() {
        return List.of(
                HappinessThemeFixture.happinessTheme().id(1L).name("theme1").build(),
                HappinessThemeFixture.happinessTheme().id(2L).name("theme2").build()
        );
    }
}
