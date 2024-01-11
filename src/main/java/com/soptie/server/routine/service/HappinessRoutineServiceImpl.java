package com.soptie.server.routine.service;

import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessThemesResponse;
import com.soptie.server.routine.repository.happiness.routine.HappinessRoutineRepository;
import com.soptie.server.routine.repository.happiness.theme.HappinessThemeRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class HappinessRoutineServiceImpl implements HappinessRoutineService {

    private final HappinessThemeRepository happinessThemeRepository;
    private final HappinessRoutineRepository happinessRoutineRepository;

    @Override
    public HappinessThemesResponse getHappinessThemes() {
        val themes = happinessThemeRepository.findAllOrderByNameAsc();
        return HappinessThemesResponse.of(themes);
    }

    @Override
    public HappinessRoutinesResponse getHappinessRoutinesByThemes(String themes) {
        val themeIds = getThemeIds(themes);
        val routines = happinessRoutineRepository.findAllByThemes(themeIds);
        return HappinessRoutinesResponse.of(routines);
    }

    private List<Long> getThemeIds(String themes) {
        val themeList = convertStringToList(themes);
        return themeList.stream().map(Long::valueOf).toList();
    }

    private List<String> convertStringToList(String themes) {
        return Arrays.stream(themes.split(",")).toList();
    }
}
