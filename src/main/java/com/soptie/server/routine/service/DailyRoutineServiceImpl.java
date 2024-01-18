package com.soptie.server.routine.service;

import static com.soptie.server.routine.message.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.routine.dto.DailyRoutinesByThemeResponse;
import com.soptie.server.routine.dto.DailyRoutinesByThemesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;

import lombok.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DailyRoutineServiceImpl implements DailyRoutineService {

	private final DailyThemeRepository dailyThemeRepository;
	private final DailyRoutineRepository dailyRoutineRepository;

	@Override
	public DailyThemesResponse getThemes() {
		val themes = dailyThemeRepository.findAllOrderByNameAsc();
		return DailyThemesResponse.of(themes);
	}

	@Override
	public DailyRoutinesByThemesResponse getRoutinesByThemes(List<Long> themeIds) {
		val routines = dailyRoutineRepository.findAllByThemes(themeIds);
		return DailyRoutinesByThemesResponse.of(routines);
	}

	@Override
	public DailyRoutinesByThemeResponse getRoutinesByTheme(long themeId) {
		val theme = findTheme(themeId);
		val routines = dailyRoutineRepository.findAllByTheme(theme);
		return DailyRoutinesByThemeResponse.of(theme, routines);
	}

	private DailyTheme findTheme(long id) {
		return dailyThemeRepository.findById(id)
			.orElseThrow(() -> new RoutineException(INVALID_THEME));
	}
}
