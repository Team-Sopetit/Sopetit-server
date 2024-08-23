package com.soptie.server.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.soptie.server.persistence.entity.MemberRoutineEntity;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutineEntity, Long> {
	@Query(
		value = "SELECT * FROM member_routine WHERE member_id = :memberId AND is_deleted = true",
		nativeQuery = true)
	List<MemberRoutineEntity> findDeletedByMemberId(long memberId);

	@Query(
		value = "SELECT * FROM member_routine WHERE is_achieved = :isAchieved AND is_deleted = true",
		nativeQuery = true)
	List<MemberRoutineEntity> findDeletedByIsAchieved(boolean isAchieved);

	List<MemberRoutineEntity> findByMemberId(long memberId);

	List<MemberRoutineEntity> findByIdIn(List<Long> ids);

	@SuppressWarnings("SpringDataMethodInconsistencyInspection")
	List<MemberRoutineEntity> findByIsAchieved(boolean isAchieved);

	@Modifying
	@Query(
		value = "DELETE FROM member_routine WHERE member_id = :memberId",
		nativeQuery = true)
	void deleteAllByMemberId(long memberId);

	void deleteAllByIdIn(List<Long> ids);
}
