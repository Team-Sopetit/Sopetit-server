package com.soptie.server.routine.repository.happiness.routine;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;

import java.util.List;

public interface HappinessRoutineCustomRepository {
    List<HappinessRoutine> findAllByThemes(List<Long> themeIds);
}
