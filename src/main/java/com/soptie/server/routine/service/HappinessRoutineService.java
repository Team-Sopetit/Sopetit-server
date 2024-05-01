package com.soptie.server.routine.service;

import com.soptie.server.routine.service.dto.response.HappinessRoutinesGetServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessSubRoutinesGetServiceResponse;

public interface HappinessRoutineService {
    HappinessRoutinesGetServiceResponse getHappinessRoutinesByTheme(Long themeId);
    HappinessSubRoutinesGetServiceResponse getHappinessSubRoutines(long routineId);
}
