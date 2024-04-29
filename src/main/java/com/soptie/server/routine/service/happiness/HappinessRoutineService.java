package com.soptie.server.routine.service.happiness;

import com.soptie.server.routine.controller.happiness.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.controller.happiness.dto.HappinessSubRoutinesResponse;
import com.soptie.server.routine.controller.happiness.dto.HappinessThemesResponse;

public interface HappinessRoutineService {
    HappinessThemesResponse getHappinessThemes();
    HappinessRoutinesResponse getHappinessRoutinesByTheme(Long themeId);
    HappinessSubRoutinesResponse getHappinessSubRoutines(long routineId);
}
