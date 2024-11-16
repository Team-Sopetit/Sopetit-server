package com.soptie.server.persistence.repository.routine;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.soptie.server.persistence.entity.routine.RoutineHistoryEntity;

public interface RoutineHistoryRepository extends JpaRepository<RoutineHistoryEntity, Long> {
	@Modifying
	@Query("DELETE FROM RoutineHistoryEntity r "
		+ "WHERE r.memberRoutineId = :memberRoutineId AND FUNCTION('DATE', r.createdAt) = :date")
	void deleteByMemberRoutineIdAndCreatedAt(long memberRoutineId, LocalDate date);
}
