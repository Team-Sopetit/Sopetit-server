package com.soptie.server.memberroutine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberroutine.entity.DeletedMemberRoutine;
import com.soptie.server.routine.entity.RoutineType;

public interface DeletedMemberRoutineRepository extends JpaRepository<DeletedMemberRoutine, Long> {
	Optional<DeletedMemberRoutine> findByMemberAndTypeAndRoutineId(Member member, RoutineType type, long routineId);

	boolean existsByMemberAndTypeAndRoutineId(Member member, RoutineType type, long routineId);

	void deleteByMember(Member member);
}
