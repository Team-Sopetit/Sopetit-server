package com.soptie.server.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.Doll;
import com.soptie.server.persistence.entity.DollType;

public interface DollRepository extends JpaRepository<Doll, Long> {
	Optional<Doll> findByDollType(DollType type);
}
