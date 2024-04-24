package com.soptie.server.routine.service.happiness;

import com.soptie.server.routine.service.happiness.dto.HappinessRoutineByThemesGetServiceResponse;
import com.soptie.server.routine.service.happiness.dto.HappinessSubRoutineListGetServiceResponse;
import com.soptie.server.routine.service.happiness.dto.HappinessThemeListGetServiceResponse;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.happiness.routine.HappinessRoutineRepository;
import com.soptie.server.routine.repository.happiness.theme.HappinessThemeRepository;
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
    private final HappinessThemeRepository happinessThemeRepository;
    private final HappinessSubRoutineService happinessSubRoutineService;

    @Override
    public HappinessThemeListGetServiceResponse getHappinessThemes() {
        val themes = happinessThemeRepository.findAllOrderByNameAsc();
        return HappinessThemeListGetServiceResponse.of(themes);
    }

    @Override
    public HappinessRoutineByThemesGetServiceResponse getHappinessRoutinesByTheme(Long themeId) {
        val routines = happinessRoutineRepository.findAllByThemeId(themeId);
        return HappinessRoutineByThemesGetServiceResponse.of(routines);
    }

    @Override
    public HappinessSubRoutineListGetServiceResponse getHappinessSubRoutines(long routineId) {
        val routine = findRoutine(routineId);
        val happinessSubRoutines = happinessSubRoutineService.getHappinessSubRoutines(routine);
        return HappinessSubRoutineListGetServiceResponse.of(routine, happinessSubRoutines);
    }

    private HappinessRoutine findRoutine(long id) {
        return happinessRoutineRepository.findById(id)
                .orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
    }
}
