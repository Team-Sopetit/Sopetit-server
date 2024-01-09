package com.soptie.server.routine.service;

import static com.soptie.server.routine.message.ErrorMessage.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.routine.dto.DailyRoutinesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;

import jakarta.persistence.EntityNotFoundException;
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
	public DailyRoutinesResponse getRoutinesByThemes(String themes) {
		val themeIds = getThemeIds(themes);
		val routines = dailyRoutineRepository.findAllByThemes(themeIds);
		return DailyRoutinesResponse.of(routines);
	}

	private List<Long> getThemeIds(String themes) {
		val themeList = convertStringToList(themes);
		return themeList.stream().map(Long::valueOf).toList();
	}

	private List<String> convertStringToList(String themes) {
		return Arrays.stream(themes.split(",")).toList();
	}

	@Override
	public DailyRoutinesResponse getRoutinesByTheme(Long themeId) {
		val theme = getTheme(themeId);
		val routines = dailyRoutineRepository.findAllByThemeOrderByContentAsc(theme);
		return DailyRoutinesResponse.of(routines);
	}

	private DailyTheme getTheme(Long id) {
		return dailyThemeRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(INVALID_THEME.getMessage()));
	}
}
