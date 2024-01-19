package com.soptie.server.routine.repository.happiness.routine;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HappinessRoutineRepository extends JpaRepository<HappinessRoutine, Long>, HappinessRoutineCustomRepository {
}
