package com.soptie.server.routine.service.happiness;

import com.soptie.server.routine.service.happiness.dto.HappinessRoutinesServiceResponse;
import com.soptie.server.routine.service.happiness.dto.HappinessSubRoutinesServiceResponse;

public interface HappinessRoutineService {
    HappinessRoutinesServiceResponse getHappinessRoutinesByTheme(Long themeId);
    HappinessSubRoutinesServiceResponse getHappinessSubRoutines(long routineId);
}
