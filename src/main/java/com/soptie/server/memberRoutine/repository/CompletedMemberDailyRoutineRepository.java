package com.soptie.server.memberRoutine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.daily.CompletedMemberDailyRoutine;
import com.soptie.server.routine.entity.daily.DailyRoutine;

public interface CompletedMemberDailyRoutineRepository extends JpaRepository<CompletedMemberDailyRoutine, Long> {
	Optional<CompletedMemberDailyRoutine> findByMemberAndRoutine(Member member, DailyRoutine routine);
	void deleteAllByMember(Member member);
}
