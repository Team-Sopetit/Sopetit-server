package com.soptie.server.routine.repository.daily.routine;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;

public interface DailyRoutineCustomRepository {
	List<DailyRoutine> findAllByThemes(List<Long> themeIds);
	List<DailyRoutine> findAllByTheme(DailyTheme theme);
}
