package com.soptie.server.persistence.repository.challenge;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.challenge.ChallengeEntity;

public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {
	List<ChallengeEntity> findByThemeId(long themeId);

	List<ChallengeEntity> findByIdIn(List<Long> ids);
}
