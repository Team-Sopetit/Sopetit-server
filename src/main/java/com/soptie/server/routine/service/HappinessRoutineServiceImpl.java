package com.soptie.server.routine.service;

import static com.soptie.server.routine.message.RoutineErrorCode.*;

import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessSubRoutinesResponse;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.happiness.routine.HappinessRoutineRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HappinessRoutineServiceImpl implements HappinessRoutineService {

    private final HappinessRoutineRepository happinessRoutineRepository;
    private final HappinessSubRoutineService happinessSubRoutineService;

    @Override
    public HappinessRoutinesResponse getHappinessRoutinesByTheme(Long themeId) {
        val routines = happinessRoutineRepository.findAllByThemeId(themeId);
        return HappinessRoutinesResponse.of(routines);
    }

    @Override
    public HappinessSubRoutinesResponse getHappinessSubRoutines(long routineId) {
        val routine = findRoutine(routineId);
        val happinessSubRoutines = happinessSubRoutineService.getHappinessSubRoutines(routine);
        return HappinessSubRoutinesResponse.of(routine, happinessSubRoutines);
    }

    private HappinessRoutine findRoutine(long id) {
        return happinessRoutineRepository.findById(id)
                .orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
    }
}
