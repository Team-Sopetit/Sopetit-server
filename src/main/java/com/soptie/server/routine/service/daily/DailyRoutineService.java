package com.soptie.server.routine.service.daily;

import java.util.List;

import com.soptie.server.routine.service.daily.dto.DailyRoutineListGetServiceResponse;
import com.soptie.server.routine.service.daily.dto.DailyThemeListGetServiceResponse;

public interface DailyRoutineService {

	DailyThemeListGetServiceResponse getThemes();
	DailyRoutineListGetServiceResponse getRoutinesByThemes(List<Long> themeIds);
	DailyRoutineListGetServiceResponse getRoutinesByTheme(long memberId, long themeId);
}
