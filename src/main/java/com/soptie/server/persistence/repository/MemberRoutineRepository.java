package com.soptie.server.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.deleted.MemberRoutine;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutine, Long>, MemberRoutineCustomRepository {
	boolean existsByMemberIdAndTypeAndRoutineId(long memberId, RoutineType type, long routineId);

	Optional<MemberRoutine> findByMemberIdAndTypeAndRoutineId(long memberId, RoutineType type, long routineId);

	@SuppressWarnings("SpringDataMethodInconsistencyInspection")
	List<MemberRoutine> findByIsAchieve(boolean isAchieve);

	void deleteByMemberId(long memberId);

	boolean existsByMemberIdAndType(long memberId, RoutineType type);

	List<MemberRoutine> findByMemberIdAndType(long memberId, RoutineType type);
}
