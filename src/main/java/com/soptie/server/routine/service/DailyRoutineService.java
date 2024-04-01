package com.soptie.server.routine.service;

import java.util.List;

import com.soptie.server.routine.dto.daily.DailyRoutinesByThemeGetResponse;
import com.soptie.server.routine.dto.daily.DailyRoutinesByThemesGetResponse;
import com.soptie.server.routine.dto.daily.DailyThemesGetResponse;

public interface DailyRoutineService {

	DailyThemesGetResponse getThemes();
	DailyRoutinesByThemesGetResponse getRoutinesByThemes(List<Long> themeIds);
	DailyRoutinesByThemeGetResponse getRoutinesByTheme(long memberId, long themeId);
}
