package com.soptie.server.routine.repository.daily.routine;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.routine.entity.daily.DailyRoutine;

public interface DailyRoutineRepository extends JpaRepository<DailyRoutine, Long>, DailyRoutineCustomRepository {
}
