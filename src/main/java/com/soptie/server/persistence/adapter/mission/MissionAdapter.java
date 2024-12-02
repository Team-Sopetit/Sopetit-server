package com.soptie.server.persistence.adapter.mission;

import java.util.List;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.challenge.Mission;
import com.soptie.server.persistence.entity.mission.MissionEntity;
import com.soptie.server.persistence.repository.mission.MissionRepository;

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

	public Mission findById(long missionId) {
		return find(missionId).toDomain();
	}

	public List<Mission> findByIds(List<Long> ids) {
		return missionRepository.findByIdIn(ids).stream().map(MissionEntity::toDomain).toList();
	}

	private MissionEntity find(long id) {
		return missionRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Mission ID: " + id));
	}
}
