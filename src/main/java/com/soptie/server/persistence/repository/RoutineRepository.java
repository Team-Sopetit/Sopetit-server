package com.soptie.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.RoutineEntity;

public interface RoutineRepository extends JpaRepository<RoutineEntity, Long>, RoutineCustomRepository {
}
