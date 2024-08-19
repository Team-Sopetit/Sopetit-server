package com.soptie.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.ChallengeEntity;

public interface ChallengeRepository extends JpaRepository<ChallengeEntity, Long> {
}
