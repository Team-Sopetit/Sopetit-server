package com.soptie.server.persistence.repository.mission;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.soptie.server.persistence.entity.mission.MemberMissionEntity;

public interface MemberMissionRepository extends JpaRepository<MemberMissionEntity, Long> {
	List<MemberMissionEntity> findByMemberIdAndMissionIdIn(long memberId, List<Long> missionIds);

	@Modifying
	@Query(
		value = "DELETE FROM member_mission WHERE member_id = :memberId",
		nativeQuery = true)
	void deleteAllByMemberId(long memberId);

	Optional<MemberMissionEntity> findByMemberId(long memberId);

	@Query(
		value = "SELECT * FROM member_mission WHERE member_id = :memberId AND is_deleted = true",
		nativeQuery = true)
	List<MemberMissionEntity> findDeletedByMemberId(long memberId);
}
