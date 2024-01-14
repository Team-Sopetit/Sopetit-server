package com.soptie.server.memberRoutine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.routine.entity.daily.DailyRoutine;

public interface MemberDailyRoutineRepository extends JpaRepository<MemberDailyRoutine, Long>, MemberDailyRoutineCustomRepository {
	boolean existsByMemberAndRoutine(Member member, DailyRoutine routine);
}
