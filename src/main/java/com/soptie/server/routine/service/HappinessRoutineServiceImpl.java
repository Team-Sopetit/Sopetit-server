package com.soptie.server.routine.service;

import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessThemesResponse;
import com.soptie.server.routine.entity.happiness.HappinessTheme;
import com.soptie.server.routine.repository.happiness.routine.HappinessRoutineRepository;
import com.soptie.server.routine.repository.happiness.theme.HappinessThemeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.soptie.server.routine.message.ErrorMessage.INVALID_THEME;

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
    public HappinessRoutinesResponse getHappinessRoutinesByTheme(Long themeId) {
        val theme = getTheme(themeId);
        val routines = happinessRoutineRepository.findAllByTheme(theme);
        return HappinessRoutinesResponse.of(routines);
    }

    private HappinessTheme getTheme(Long id) {
        return happinessThemeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_THEME.getMessage()));
    }
}
