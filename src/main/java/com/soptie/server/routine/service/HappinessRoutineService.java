package com.soptie.server.routine.service;

import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessSubRoutinesResponse;
import com.soptie.server.routine.dto.HappinessThemesResponse;

public interface HappinessRoutineService {
    HappinessThemesResponse getHappinessThemes();

    HappinessRoutinesResponse getHappinessRoutinesByTheme(Long themeId);
    HappinessSubRoutinesResponse getHappinessSubRoutines(long routineId);
}
