package com.soptie.server.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.entity.MemberRoutine;
import com.soptie.server.persistence.entity.RoutineType;

public interface MemberRoutineRepository extends JpaRepository<MemberRoutine, Long>, MemberRoutineCustomRepository {
	boolean existsByMemberAndTypeAndRoutineId(Member member, RoutineType type, long routineId);

	Optional<MemberRoutine> findByMemberAndTypeAndRoutineId(Member member, RoutineType type, long routineId);

	@SuppressWarnings("SpringDataMethodInconsistencyInspection")
	List<MemberRoutine> findByIsAchieve(boolean isAchieve);

	void deleteByMember(Member member);

	boolean existsByMemberAndType(Member member, RoutineType type);

	List<MemberRoutine> findByMemberAndType(Member member, RoutineType type);
}
