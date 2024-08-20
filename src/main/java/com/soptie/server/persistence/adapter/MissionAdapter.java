package com.soptie.server.persistence.adapter;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.challenge.Mission;
import com.soptie.server.persistence.entity.MissionEntity;
import com.soptie.server.persistence.repository.MissionRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MissionAdapter {
	private final MissionRepository missionRepository;

	public List<Mission> findByChallengeIds(List<Long> challengeIds) {
		return missionRepository.findByChallengeIdIn(challengeIds)
			.stream().map(MissionEntity::toDomain)
			.toList();
	}
}
