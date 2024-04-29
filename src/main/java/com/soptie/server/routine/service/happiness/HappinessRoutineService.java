package com.soptie.server.routine.service.happiness;

import com.soptie.server.routine.service.happiness.dto.HappinessRoutinesServiceResponse;
import com.soptie.server.routine.service.happiness.dto.HappinessSubRoutinesServiceResponse;
import com.soptie.server.routine.service.happiness.dto.HappinessThemesServiceResponse;

public interface HappinessRoutineService {
    HappinessThemesServiceResponse getHappinessThemes();
    HappinessRoutinesServiceResponse getHappinessRoutinesByTheme(Long themeId);
    HappinessSubRoutinesServiceResponse getHappinessSubRoutines(long routineId);
}
