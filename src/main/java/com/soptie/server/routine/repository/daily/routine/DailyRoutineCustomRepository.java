package com.soptie.server.routine.repository.daily.routine;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyRoutine;

public interface DailyRoutineCustomRepository {
	List<DailyRoutine> findAllByThemes(List<Long> themeIds);
}
