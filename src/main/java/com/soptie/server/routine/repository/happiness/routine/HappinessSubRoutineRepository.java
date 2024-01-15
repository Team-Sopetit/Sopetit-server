package com.soptie.server.routine.repository.happiness.routine;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;

import java.util.List;

public interface HappinessSubRoutineRepository extends JpaRepository<HappinessSubRoutine, Long>, HappinessSubRoutineCustomRepository {
}
