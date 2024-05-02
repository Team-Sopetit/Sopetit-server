package com.soptie.server.routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.routine.entity.Routine;

public interface RoutineRepository extends JpaRepository<Routine, Long>, RoutineCustomRepository {
}
