package com.soptie.server.routine.service;

import java.util.List;

import com.soptie.server.routine.dto.DailyRoutinesByThemeResponse;
import com.soptie.server.routine.dto.DailyRoutinesByThemesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;

public interface DailyRoutineService {

	DailyThemesResponse getThemes();
	DailyRoutinesByThemesResponse getRoutinesByThemes(List<Long> themeIds);
	DailyRoutinesByThemeResponse getRoutinesByTheme(long themeId);
}
