package com.soptie.server.memberRoutine.repository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHappinessRoutineRepository extends JpaRepository<MemberHappinessRoutine, Long> {
    boolean existByMemberAndHappinessRoutine(Member member, HappinessRoutine routine);
}
