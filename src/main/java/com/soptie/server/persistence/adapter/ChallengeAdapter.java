package com.soptie.server.persistence.adapter;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.persistence.entity.ChallengeEntity;
import com.soptie.server.persistence.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ChallengeAdapter {
	private final ChallengeRepository challengeRepository;

	public List<Challenge> findByThemeId(long themeId) {
		return challengeRepository.findByThemeId(themeId).stream().map(ChallengeEntity::toDomain).toList();
	}
}
