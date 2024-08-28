package com.soptie.server.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.domain.doll.DollType;
import com.soptie.server.persistence.entity.DollEntity;

public interface DollRepository extends JpaRepository<DollEntity, Long> {
	Optional<DollEntity> findByType(DollType type);
}
