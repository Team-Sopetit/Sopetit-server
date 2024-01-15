package com.soptie.server.routine.service;

import com.soptie.server.routine.dto.HappinessSubRoutinesResponse;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;

import java.util.List;

public interface HappinessSubRoutineService {
    List<HappinessSubRoutine> getHappinessSubRoutines(HappinessRoutine routine);
}
