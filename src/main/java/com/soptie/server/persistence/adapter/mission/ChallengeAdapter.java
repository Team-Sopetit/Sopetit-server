package com.soptie.server.persistence.adapter.mission;

import java.util.List;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.persistence.entity.mission.ChallengeEntity;
import com.soptie.server.persistence.repository.mission.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ChallengeAdapter {
	private final ChallengeRepository challengeRepository;

	public List<Challenge> findByThemeId(long themeId) {
		return challengeRepository.findByThemeId(themeId).stream().map(ChallengeEntity::toDomain).toList();
	}

	public Challenge findById(long challengeId) {
		return find(challengeId).toDomain();
	}

	private ChallengeEntity find(long id) {
		return challengeRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Challenge ID: " + id));
	}
}
