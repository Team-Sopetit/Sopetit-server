package com.soptie.server.routine.repository.happiness.routine;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessTheme;

import java.util.List;

public interface HappinessRoutineCustomRepository {
    List<HappinessRoutine> findAllByThemeId(Long themeId);
}
