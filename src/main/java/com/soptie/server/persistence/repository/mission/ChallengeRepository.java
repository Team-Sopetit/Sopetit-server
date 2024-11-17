package com.soptie.server.persistence.repository.mission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.mission.ChallengeEntity;

public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {
	List<ChallengeEntity> findByThemeId(long themeId);
}
