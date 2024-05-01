package com.soptie.server.memberRoutine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.DeletedMemberRoutine;
import com.soptie.server.routine.entity.RoutineType;

public interface DeletedMemberRoutineRepository extends JpaRepository<DeletedMemberRoutine, Long> {
	Optional<DeletedMemberRoutine> findByMemberAndTypeAndRoutineId(Member member, RoutineType type, long routineId);
	boolean existsByMemberAndTypeAndRoutineId(Member member, RoutineType type, long routineId);
}
