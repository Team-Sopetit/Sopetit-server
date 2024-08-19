package com.soptie.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.Routine;

public interface RoutineRepository extends JpaRepository<Routine, Long>, RoutineCustomRepository {
}
