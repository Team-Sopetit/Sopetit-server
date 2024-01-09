package com.soptie.server.doll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.doll.entity.Doll;
import com.soptie.server.doll.entity.DollType;

public interface DollRepository extends JpaRepository<Doll, Long> {
	Optional<Doll> findByDollType(DollType type);
}
