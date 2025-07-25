package com.soptie.server.persistence.repository.routine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.soptie.server.persistence.entity.routine.MemberRoutineEntity;
import com.soptie.server.persistence.repository.routine.custom.MemberRoutineCustomRepository;
import com.soptie.server.persistence.repository.utils.QueryUtils;

public interface MemberRoutineRepository
	extends JpaRepository<MemberRoutineEntity, Long>, MemberRoutineCustomRepository {

	/**
	 * find
	 */

	@Query(
		value = "SELECT * FROM member_routine WHERE member_id = :memberId AND is_deleted = true",
		nativeQuery = true)
	List<MemberRoutineEntity> findDeletedByMemberId(long memberId);

	List<MemberRoutineEntity> findByMemberId(long memberId);

	List<MemberRoutineEntity> findByIdIn(List<Long> ids);

	List<MemberRoutineEntity> findByMemberIdAndRoutineIdIn(long memberId, List<Long> routineIds);

	/**
	 * delete
	 */

	@Modifying
	@Query(
		value = "DELETE FROM member_routine WHERE member_id = :memberId",
		nativeQuery = true)
	void deleteAllByMemberId(long memberId);

	void deleteAllByIdIn(List<Long> ids);

	@Modifying
	@Query(value = QueryUtils.DELETE_FORCE_MEMBER_ROUTINE, nativeQuery = true)
	void deleteForceById(long id);
}
