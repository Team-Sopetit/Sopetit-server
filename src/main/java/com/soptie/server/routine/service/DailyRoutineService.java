package com.soptie.server.routine.service;

import java.util.List;

import com.soptie.server.routine.dto.DailyRoutinesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;

public interface DailyRoutineService {

	DailyThemesResponse getThemes();
	DailyRoutinesResponse getRoutinesByThemes(List<Long> themeIds);
	DailyRoutinesResponse getRoutinesByTheme(long themeId);
}
