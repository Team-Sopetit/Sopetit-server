package com.soptie.server.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.MemberRoutineEntity;

public interface MemberRoutineRepository
	extends JpaRepository<MemberRoutineEntity, Long>, MemberRoutineCustomRepository {
	List<MemberRoutineEntity> findByMemberId(long memberId);

	boolean existsByMemberIdAndTypeAndRoutineId(long memberId, RoutineType type, long routineId);

	Optional<MemberRoutine> findByMemberIdAndTypeAndRoutineId(long memberId, RoutineType type, long routineId);

	@SuppressWarnings("SpringDataMethodInconsistencyInspection")
	List<MemberRoutine> findByIsAchieve(boolean isAchieve);

	void deleteByMemberId(long memberId);

	boolean existsByMemberIdAndType(long memberId, RoutineType type);
}
