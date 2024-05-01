package com.soptie.server.memberRoutine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.memberRoutine.entity.MemberRoutine;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutine, Long> {
}
