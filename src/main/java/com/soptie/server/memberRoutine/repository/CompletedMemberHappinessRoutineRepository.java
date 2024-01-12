package com.soptie.server.memberRoutine.repository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.happiness.CompletedMemberHappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompletedMemberHappinessRoutineRepository extends JpaRepository<CompletedMemberHappinessRoutine, Long> {
    Optional<CompletedMemberHappinessRoutine> findByMemberAndRoutine(Member member, HappinessRoutine routine);
}
