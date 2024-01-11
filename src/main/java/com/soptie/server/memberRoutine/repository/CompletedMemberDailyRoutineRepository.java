package com.soptie.server.memberRoutine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.memberRoutine.entity.daily.CompletedMemberDailyRoutine;

public interface CompletedMemberDailyRoutineRepository extends JpaRepository<CompletedMemberDailyRoutine, Long> {
}
