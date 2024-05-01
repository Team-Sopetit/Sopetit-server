package com.soptie.server.memberRoutine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.routine.entity.RoutineType;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutine, Long> {
	boolean existsByMemberAndTypeAndRoutineId(Member member, RoutineType type, long routineId);
	Optional<MemberRoutine> findByMemberAndTypeAndRoutineId(Member member, RoutineType type, long routineId);
	List<MemberRoutine> findByAchieveIsTrue();
	void deleteByMember(Member member);
}
