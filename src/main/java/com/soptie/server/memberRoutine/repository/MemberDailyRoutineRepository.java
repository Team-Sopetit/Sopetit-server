package com.soptie.server.memberRoutine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

public interface MemberDailyRoutineRepository extends JpaRepository<MemberDailyRoutine, Long>, MemberDailyRoutineCustomRepository {
}
