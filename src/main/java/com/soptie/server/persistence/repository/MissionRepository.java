package com.soptie.server.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.MissionEntity;

public interface MissionRepository extends JpaRepository<MissionEntity, Long> {
	List<MissionEntity> findByChallengeIdIn(List<Long> challengeIds);
}
