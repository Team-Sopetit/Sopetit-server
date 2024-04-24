package com.soptie.server.routine.service.happiness;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;
import com.soptie.server.routine.repository.happiness.routine.HappinessSubRoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HappinessSubRoutineServiceImpl implements HappinessSubRoutineService {

    private final HappinessSubRoutineRepository happinessSubRoutineRepository;

    @Override
    public List<HappinessSubRoutine> getHappinessSubRoutines(HappinessRoutine routine) {
        return happinessSubRoutineRepository.findAllByRoutine(routine);
    }
}
