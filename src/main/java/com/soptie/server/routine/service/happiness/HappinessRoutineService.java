package com.soptie.server.routine.service.happiness;

import com.soptie.server.routine.service.happiness.dto.HappinessRoutineByThemesGetServiceResponse;
import com.soptie.server.routine.service.happiness.dto.HappinessSubRoutineListGetServiceResponse;
import com.soptie.server.routine.service.happiness.dto.HappinessThemeListGetServiceResponse;

public interface HappinessRoutineService {
    HappinessThemeListGetServiceResponse getHappinessThemes();
    HappinessRoutineByThemesGetServiceResponse getHappinessRoutinesByTheme(Long themeId);
    HappinessSubRoutineListGetServiceResponse getHappinessSubRoutines(long routineId);
}
