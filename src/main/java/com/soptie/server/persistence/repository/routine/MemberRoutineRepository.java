package com.soptie.server.persistence.repository.routine;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.soptie.server.persistence.entity.routine.MemberRoutineEntity;
import com.soptie.server.persistence.repository.routine.custom.MemberRoutineCustomRepository;
import com.soptie.server.persistence.support.QueryConstants;

public interface MemberRoutineRepository
	extends JpaRepository<MemberRoutineEntity, Long>, MemberRoutineCustomRepository {

	/**
	 * find
	 */

	@Query(value = QueryConstants.FIND_DELETED_MEMBER_ROUTINES_IN_TARGETS, nativeQuery = true)
	List<MemberRoutineEntity> findDeletedByMemberId(long memberId, List<Long> targetIds);

	List<MemberRoutineEntity> findByMemberId(long memberId);

	List<MemberRoutineEntity> findByIdIn(List<Long> ids);

	@Query(value = QueryConstants.FIND_BY_ID, nativeQuery = true)
	Optional<MemberRoutineEntity> findByIdRegardlessOfDeleted(long id);

	/**
	 * delete
	 */

	@Modifying
	@Query(value = QueryConstants.FORCE_DELETE_MEMBER_ROUTINES, nativeQuery = true)
	void deleteAllByMemberId(long memberId);

	void deleteAllByIdIn(List<Long> ids);

	@Modifying
	@Query(value = QueryConstants.DELETE_FORCE_MEMBER_ROUTINE, nativeQuery = true)
	void deleteForceById(long id);
}
