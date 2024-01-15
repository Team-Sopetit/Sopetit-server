package com.soptie.server.routine.repository.happiness.routine;

import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;

import java.util.List;

public interface HappinessSubRoutineCustomRepository {

    List<HappinessSubRoutine> findAllByRoutineId(Long routineId);
}
