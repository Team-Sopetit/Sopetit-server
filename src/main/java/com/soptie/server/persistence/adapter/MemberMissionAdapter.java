package com.soptie.server.persistence.adapter;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.membermission.MemberMission;
import com.soptie.server.persistence.entity.MemberMissionEntity;
import com.soptie.server.persistence.repository.MemberMissionRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberMissionAdapter {
	private final MemberMissionRepository memberMissionRepository;

	public List<MemberMission> findByMemberIdAndMissionIds(long memberId, List<Long> missionIds) {
		return memberMissionRepository.findByMemberIdAndMissionIdIn(memberId, missionIds)
			.stream().map(MemberMissionEntity::toDomain)
			.toList();
	}
}
