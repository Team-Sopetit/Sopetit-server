package com.soptie.server.routine.repository.happiness.routine;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessTheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HappinessRoutineRepository extends JpaRepository<HappinessRoutine, Long>, HappinessRoutineCustomRepository {
    List<HappinessRoutine> findAllByThemeOrderByContentAsc(HappinessTheme theme);
}
