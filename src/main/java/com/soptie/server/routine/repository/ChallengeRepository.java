package com.soptie.server.routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.routine.entity.challenge.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
