package com.soptie.server.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soptie.server.persistence.entity.MemberMissionEntity;

public interface MemberMissionRepository extends JpaRepository<MemberMissionEntity, Long> {
	List<MemberMissionEntity> findByMemberIdAndMissionIdIn(long memberId, List<Long> missionIds);

	Optional<MemberMissionEntity> findByMemberId(long memberId);

	@Query("SELECT mm FROM MemberMissionEntity mm WHERE mm.memberId = :memberId AND mm.isDeleted = true")
	List<MemberMissionEntity> findDeletedByMemberId(long memberId);
}
