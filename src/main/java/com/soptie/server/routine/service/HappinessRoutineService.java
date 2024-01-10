package com.soptie.server.routine.service;


import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessThemesResponse;

public interface HappinessRoutineService {
    HappinessThemesResponse getHappinessThemes();

    HappinessRoutinesResponse getHappinessRoutinesByThemes(String themes);
}
