package com.soptie.server.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.deleted.DeletedMemberRoutine;

public interface DeletedMemberRoutineRepository extends JpaRepository<DeletedMemberRoutine, Long> {
	Optional<DeletedMemberRoutine> findByMemberIdAndTypeAndRoutineId(long memberId, RoutineType type, long routineId);

	boolean existsByMemberIdAndTypeAndRoutineId(long memberId, RoutineType type, long routineId);

	void deleteByMemberId(long memberId);
}
