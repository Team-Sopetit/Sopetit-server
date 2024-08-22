package com.soptie.server.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soptie.server.persistence.entity.MemberRoutineEntity;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutineEntity, Long> {
	@Query("SELECT mr FROM MemberRoutineEntity mr WHERE mr.memberId = :memberId AND mr.isDeleted = true")
	List<MemberRoutineEntity> findDeletedByMemberId(long memberId);

	@Query("SELECT mr FROM MemberRoutineEntity mr WHERE mr.isAchieved = :isAchieved AND mr.isDeleted = true")
	List<MemberRoutineEntity> findDeletedByIsAchieved(boolean isAchieved);

	List<MemberRoutineEntity> findByMemberId(long memberId);

	List<MemberRoutineEntity> findByIdIn(List<Long> ids);

	@SuppressWarnings("SpringDataMethodInconsistencyInspection")
	List<MemberRoutineEntity> findByIsAchieved(boolean isAchieved);

	void deleteAllByMemberId(long memberId);

	void deleteAllByIdIn(List<Long> ids);
}
