package com.soptie.server.persistence.adapter;

import java.util.List;
import java.util.Optional;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.challenge.Mission;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.membermission.MemberMission;
import com.soptie.server.persistence.entity.MemberMissionEntity;
import com.soptie.server.persistence.repository.MemberMissionRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberMissionAdapter {
	private final MemberMissionRepository memberMissionRepository;

	public List<MemberMission> findByMemberIdAndMissionIds(long memberId, List<Long> missionIds) {
		return memberMissionRepository.findByMemberIdAndMissionIdIn(memberId, missionIds)
			.stream().map(MemberMissionEntity::toDomain)
			.toList();
	}

	public void deleteAllByMemberId(long memberId) {
		memberMissionRepository.deleteAllByMemberId(memberId);
	}

	public Optional<MemberMission> findByMember(long memberId) {
		return memberMissionRepository.findByMemberId(memberId).map(MemberMissionEntity::toDomain);
	}

	public void delete(MemberMission memberMission) {
		val memberMissionEntity = find(memberMission.getId());
		memberMissionRepository.delete(memberMissionEntity);
	}

	public void update(MemberMission memberMission) {
		val memberMissionEntity = find(memberMission.getId());
		memberMissionEntity.update(memberMission);
	}

	public MemberMission save(Member member, Mission mission) {
		val deletedMemberMissions = memberMissionRepository.findDeletedByMemberId(member.getId());
		val deletedMemberMissionIds = deletedMemberMissions.stream().map(MemberMissionEntity::getMissionId).toList();
		return (deletedMemberMissionIds.contains(mission.getId())
			? restore(deletedMemberMissions, member, mission)
			: memberMissionRepository.save(new MemberMissionEntity(member, mission)))
			.toDomain();
	}

	private MemberMissionEntity restore(
		List<MemberMissionEntity> deletedMemberMissions,
		Member member,
		Mission mission
	) {
		val deletedMemberMission = deletedMemberMissions.stream()
			.filter(mm -> mm.getMissionId() == mission.getId())
			.findFirst()
			.orElseThrow(() -> new SoftieException(
				ExceptionCode.NOT_FOUND,
				"Member ID: " + member.getId() + " Mission ID: " + mission.getId()));
		deletedMemberMission.restore();
		return deletedMemberMission;
	}

	private MemberMissionEntity find(long id) {
		return memberMissionRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "MemberMission ID: " + id));
	}
}
