package com.soptie.server.persistence.adapter.challenge;

import java.util.List;
import java.util.Objects;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.common.utils.CollectionUtils;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.persistence.entity.challenge.ChallengeEntity;
import com.soptie.server.persistence.global.ChallengeStore;
import com.soptie.server.persistence.repository.challenge.ChallengeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryAdapter
@RequiredArgsConstructor
public class ChallengeAdapter {

	private final ChallengeRepository challengeRepository;
	private final ChallengeStore challengeStore;

	public List<Challenge> findAllByTheme(long themeId) {
		return challengeRepository.findByThemeId(themeId).stream().map(ChallengeEntity::toDomain).toList();
	}

	public Challenge findById(long challengeId) {
		return find(challengeId).toDomain();
	}

	private ChallengeEntity find(long id) {
		return challengeRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Challenge ID: " + id));
	}

	public List<Challenge> findByIds(List<Long> challengeIds) {
		try {
			List<Challenge> challenges = challengeIds.stream()
				.map(challengeStore::get)
				.filter(Objects::nonNull)
				.toList();

			return CollectionUtils.isNotEmpty(challenges) ? challenges : findByIdsFallback(challengeIds);
		} catch (Exception e) {
			return findByIdsFallback(challengeIds);
		}
	}

	private List<Challenge> findByIdsFallback(List<Long> ids) {
		return challengeRepository.findByIdIn(ids)
			.stream()
			.map(ChallengeEntity::toDomain)
			.toList();
	}
}
