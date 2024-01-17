package com.soptie.server.routine.service;

import static com.soptie.server.routine.message.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.routine.dto.DailyRoutinesResponse;
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
	public DailyRoutinesResponse getRoutinesByThemes(List<Long> themeIds) {
		val routines = dailyRoutineRepository.findAllByThemes(themeIds);
		return DailyRoutinesResponse.of(routines);
	}

	@Override
	public DailyRoutinesResponse getRoutinesByTheme(long themeId) {
		val theme = findTheme(themeId);
		val routines = dailyRoutineRepository.findAllByTheme(theme);
		return DailyRoutinesResponse.of(routines);
	}

	private DailyTheme findTheme(long id) {
		return dailyThemeRepository.findById(id)
			.orElseThrow(() -> new RoutineException(INVALID_THEME));
	}
}
