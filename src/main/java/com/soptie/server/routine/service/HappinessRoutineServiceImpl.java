package com.soptie.server.routine.service;

import com.soptie.server.routine.dto.HappinessThemesResponse;
import com.soptie.server.routine.repository.happiness.theme.HappinessThemeRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class HappinessRoutineServiceImpl implements HappinessRoutineService{

    private final HappinessThemeRepository happinessThemeRepository;

    @Override
    public HappinessThemesResponse getHappinessThemes() {
        val themes = happinessThemeRepository.findAllOrderByNameAsc();
        return HappinessThemesResponse.of(themes);
    }
}
