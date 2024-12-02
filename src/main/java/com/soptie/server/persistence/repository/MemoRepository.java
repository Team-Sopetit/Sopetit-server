package com.soptie.server.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soptie.server.persistence.entity.MemoEntity;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
	Optional<MemoEntity> findByIdAndMemberId(long id, long memberId);

	void deleteByIdAndMemberId(long id, long memberId);

	@Query("SELECT m FROM MemoEntity m WHERE m.memberId = :memberId AND m.achievedDate BETWEEN :startDate AND :endDate")
	List<MemoEntity> findAllByMemberIdAndAchievedDateBetween(
		@Param("memberId") long memberId,
		@Param("startDate") LocalDate startDate,
		@Param("endDate") LocalDate endDate
	);
}
