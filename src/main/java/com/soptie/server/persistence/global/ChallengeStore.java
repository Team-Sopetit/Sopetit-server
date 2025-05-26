package com.soptie.server.persistence.global;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mysema.commons.lang.Pair;
import com.soptie.server.config.support.GlobalData;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.persistence.entity.challenge.ChallengeEntity;
import com.soptie.server.persistence.repository.challenge.ChallengeRepository;

import io.jsonwebtoken.lang.Collections;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChallengeStore {

	private final ChallengeRepository challengeRepository;

	@Getter
	@GlobalData
	private Map<Long, Challenge> challenges;

	private LocalDate updateDate;

	@Scheduled(cron = "0 */10 * * * *")
	public void init() {
		if (!challenges.isEmpty() && updateDate.isEqual(LocalDate.now())) {
			return;
		}

		try {
			update();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			challenges = new HashMap<>();
		}
	}

	public Challenge get(long id) {
		if (Collections.isEmpty(challenges)) {
			update();
		}

		if (!challenges.containsKey(id)) {
			return null;
		}

		return challenges.get(id);
	}

	public List<Challenge> getAll() {
		return challenges.values().stream().toList();
	}

	private void update() {
		challenges = challengeRepository.findAll()
			.stream()
			.map(ChallengeEntity::toDomain)
			.map(challenge -> Pair.of(challenge.getId(), challenge))
			.filter(routine -> routine.getFirst() != null && routine.getSecond() != null)
			.collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));

		updateDate = LocalDate.now();
	}
}
