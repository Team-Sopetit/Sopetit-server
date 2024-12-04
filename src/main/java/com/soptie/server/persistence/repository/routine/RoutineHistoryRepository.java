package com.soptie.server.persistence.repository.routine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soptie.server.persistence.entity.routine.RoutineHistoryEntity;

public interface RoutineHistoryRepository extends JpaRepository<RoutineHistoryEntity, Long> {
	@Modifying
	@Query("DELETE FROM RoutineHistoryEntity r "
		+ "WHERE r.memberRoutineId = :memberRoutineId AND FUNCTION('DATE', r.createdAt) = :date")
	void deleteByMemberRoutineIdAndCreatedAt(long memberRoutineId, LocalDate date);

	@Query(value = "SELECT * FROM routine_history r "
		+ "WHERE r.member_id = :memberId AND DATE(r.created_at) = :date LIMIT 1", nativeQuery = true)
	Optional<RoutineHistoryEntity> findByMemberIdAndCreatedAt(long memberId, LocalDate date);

	@Query("SELECT r FROM RoutineHistoryEntity r WHERE r.memberId = :memberId "
		+ "AND r.createdAt BETWEEN :startDateTime AND :endDateTime")
	List<RoutineHistoryEntity> findAllByMemberIdAndCreatedAtBetween(
		@Param("memberId") long memberId,
		@Param("startDateTime") LocalDateTime startDateTime,
		@Param("endDateTime") LocalDateTime endDateTime
	);
}
