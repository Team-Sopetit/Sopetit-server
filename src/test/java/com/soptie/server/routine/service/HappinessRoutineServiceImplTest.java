package com.soptie.server.routine.service;

import com.soptie.server.routine.controller.happiness.dto.HappinessRoutinesGetResponse;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessTheme;
import com.soptie.server.routine.repository.happiness.routine.HappinessRoutineRepository;
import com.soptie.server.support.fixture.HappinessRoutineFixture;
import com.soptie.server.support.fixture.HappinessThemeFixture;
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
    private HappinessRoutineRepository happinessRoutineRepository;

    @Test
    void 주어진_테마에_속하는_행복_루틴_목록을_조회합니다() {
        // given
        long themeId = 1L;
        HappinessTheme theme = HappinessThemeFixture.happinessTheme().name("사랑").nameColor("빨간색").imageUrl("imageInfo").build();


        HappinessRoutine routine1 = HappinessRoutineFixture.happinessRoutine().id(1L).title("루틴1").theme(theme).build();
        HappinessRoutine routine2 = HappinessRoutineFixture.happinessRoutine().id(2L).title("루틴2").theme(theme).build();
        List<HappinessRoutine> routines = Arrays.asList(routine1, routine2);

        doReturn(routines).when(happinessRoutineRepository).findAllByThemeId(themeId);

        // when
        HappinessRoutinesGetResponse response = HappinessRoutinesGetResponse.of(happinessRoutineService.getHappinessRoutinesByTheme(themeId));

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
