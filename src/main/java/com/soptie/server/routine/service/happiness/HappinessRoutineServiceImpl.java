package com.soptie.server.routine.service.happiness;

import com.soptie.server.routine.service.happiness.dto.HappinessRoutinesServiceResponse;
import com.soptie.server.routine.service.happiness.dto.HappinessSubRoutinesServiceResponse;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.happiness.routine.HappinessRoutineRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.soptie.server.routine.message.ErrorCode.INVALID_ROUTINE;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HappinessRoutineServiceImpl implements HappinessRoutineService {

    private final HappinessRoutineRepository happinessRoutineRepository;
    private final HappinessSubRoutineService happinessSubRoutineService;

    @Override
    public HappinessRoutinesServiceResponse getHappinessRoutinesByTheme(Long themeId) {
        val routines = happinessRoutineRepository.findAllByThemeId(themeId);
        return HappinessRoutinesServiceResponse.of(routines);
    }

    @Override
    public HappinessSubRoutinesServiceResponse getHappinessSubRoutines(long routineId) {
        val routine = findRoutine(routineId);
        val happinessSubRoutines = happinessSubRoutineService.getHappinessSubRoutines(routine);
        return HappinessSubRoutinesServiceResponse.of(routine, happinessSubRoutines);
    }

    private HappinessRoutine findRoutine(long id) {
        return happinessRoutineRepository.findById(id)
                .orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
    }
}
