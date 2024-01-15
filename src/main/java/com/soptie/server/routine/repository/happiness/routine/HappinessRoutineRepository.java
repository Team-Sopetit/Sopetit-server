package com.soptie.server.routine.repository.happiness.routine;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HappinessRoutineRepository extends JpaRepository<HappinessRoutine, Long>, HappinessRoutineCustomRepository {
}
