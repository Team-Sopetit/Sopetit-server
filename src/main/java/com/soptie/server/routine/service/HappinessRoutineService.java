package com.soptie.server.routine.service;

import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessSubRoutinesResponse;

public interface HappinessRoutineService {
    HappinessRoutinesResponse getHappinessRoutinesByTheme(Long themeId);
    HappinessSubRoutinesResponse getHappinessSubRoutines(long routineId);
}
