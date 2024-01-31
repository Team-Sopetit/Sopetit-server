package com.soptie.server.routine.repository.daily.theme;

import java.util.List;

import com.soptie.server.routine.entity.daily.DailyTheme;

public interface DailyThemeCustomRepository {
	List<DailyTheme> findAllOrderByNameAsc();
}
