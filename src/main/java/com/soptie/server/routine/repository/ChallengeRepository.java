package com.soptie.server.routine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
	List<Challenge> findByRoutine(Routine routine);
}
