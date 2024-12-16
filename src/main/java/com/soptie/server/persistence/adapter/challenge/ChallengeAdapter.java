package com.soptie.server.persistence.adapter.challenge;

import java.util.List;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.persistence.entity.challenge.ChallengeEntity;
import com.soptie.server.persistence.repository.challenge.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ChallengeAdapter {
	private final ChallengeRepository challengeRepository;

	public List<Challenge> findAllByTheme(long themeId) {
		return challengeRepository.findByThemeId(themeId).stream().map(ChallengeEntity::toDomain).toList();
	}

	public Challenge findById(long missionId) {
		return find(missionId).toDomain();
	}

	private ChallengeEntity find(long id) {
		return challengeRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Mission ID: " + id));
	}
}
