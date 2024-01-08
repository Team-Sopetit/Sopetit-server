package com.soptie.server.routine.service;

import org.springframework.stereotype.Service;

import com.soptie.server.routine.dto.DailyThemesResponse;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;

import lombok.*;

@Service
@RequiredArgsConstructor
public class DailyRoutineService {

	private final DailyThemeRepository dailyThemeRepository;

	public DailyThemesResponse getThemes() {
		val themes = dailyThemeRepository.findAllOrderByNameAsc();
		return DailyThemesResponse.of(themes);
	}
}
