package com.soptie.server.routine.service;

import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessSubRoutinesResponse;
import com.soptie.server.routine.dto.HappinessThemesResponse;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.repository.happiness.routine.HappinessRoutineRepository;
import com.soptie.server.routine.repository.happiness.theme.HappinessThemeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.soptie.server.routine.message.ErrorMessage.INVALID_ROUTINE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HappinessRoutineServiceImpl implements HappinessRoutineService {

    private final HappinessRoutineRepository happinessRoutineRepository;
    private final HappinessThemeRepository happinessThemeRepository;
    private final HappinessSubRoutineService happinessSubRoutineService;

    @Override
    public HappinessThemesResponse getHappinessThemes() {
        val themes = happinessThemeRepository.findAllOrderByNameAsc();
        return HappinessThemesResponse.of(themes);
    }

    @Override
    public HappinessRoutinesResponse getHappinessRoutinesByTheme(Long themeId) {
        val routines = happinessRoutineRepository.findAllByThemeId(themeId);
        return HappinessRoutinesResponse.of(routines);
    }

    @Override
    public HappinessSubRoutinesResponse getHappinessSubRoutines(Long routineId) {
        val routine = findRoutine(routineId);
        val happinessSubRoutines = happinessSubRoutineService.getHappinessSubRoutines(routine);
        return HappinessSubRoutinesResponse.of(happinessSubRoutines);
    }

    private HappinessRoutine findRoutine(Long routineId) {
        return happinessRoutineRepository.findById(routineId)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_ROUTINE.getMessage()));
    }
}
