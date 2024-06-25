package com.soptie.server.memberroutine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberroutine.entity.MemberRoutine;
import com.soptie.server.routine.entity.RoutineType;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutine, Long>, MemberRoutineCustomRepository {
	boolean existsByMemberAndTypeAndRoutineId(Member member, RoutineType type, long routineId);

	Optional<MemberRoutine> findByMemberAndTypeAndRoutineId(Member member, RoutineType type, long routineId);

	@SuppressWarnings("SpringDataMethodInconsistencyInspection")
	List<MemberRoutine> findByIsAchieve(boolean isAchieve);

	void deleteByMember(Member member);

	boolean existsByMemberAndType(Member member, RoutineType type);
}
