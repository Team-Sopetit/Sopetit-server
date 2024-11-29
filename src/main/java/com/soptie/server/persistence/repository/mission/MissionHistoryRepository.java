package com.soptie.server.persistence.repository.mission;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soptie.server.persistence.entity.mission.MissionHistoryEntity;

public interface MissionHistoryRepository extends JpaRepository<MissionHistoryEntity, Long> {

	@Query(value = "SELECT * FROM mission_history m "
		+ "WHERE m.member_id = :memberId AND DATE(m.created_at) = :date LIMIT 1", nativeQuery = true)
	Optional<MissionHistoryEntity> findByMemberIdAndCreatedAt(long memberId, LocalDate date);
}
