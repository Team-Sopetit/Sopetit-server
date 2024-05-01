package com.soptie.server.routine.service;

import com.soptie.server.routine.controller.happiness.dto.HappinessRoutinesGetResponse;
import com.soptie.server.routine.controller.happiness.dto.HappinessSubRoutinesGetResponse;

public interface HappinessRoutineService {
    HappinessRoutinesGetResponse getHappinessRoutinesByTheme(Long themeId);
    HappinessSubRoutinesGetResponse getHappinessSubRoutines(long routineId);
}
