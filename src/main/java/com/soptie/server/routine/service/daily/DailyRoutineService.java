package com.soptie.server.routine.service.daily;

import java.util.List;

import com.soptie.server.routine.service.daily.dto.DailyRoutineListGetServiceResponse;

public interface DailyRoutineService {
	DailyRoutineListGetServiceResponse getRoutinesByThemes(List<Long> themeIds);
	DailyRoutineListGetServiceResponse getRoutinesByTheme(long memberId, long themeId);
}
