package com.soptie.server.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.Challenge;
import com.soptie.server.persistence.entity.Routine;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
	List<Challenge> findByRoutine(Routine routine);
}
