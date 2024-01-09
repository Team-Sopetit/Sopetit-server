package com.soptie.server.routine.service;

import com.soptie.server.routine.dto.DailyRoutinesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;

public interface DailyRoutineService {

	DailyThemesResponse getThemes();
	DailyRoutinesResponse getRoutinesByThemes(String themes);
}
