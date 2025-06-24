package com.soptie.server.persistence.global;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mysema.commons.lang.Pair;
import com.soptie.server.common.utils.MapUtils;
import com.soptie.server.config.support.GlobalData;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.persistence.entity.challenge.ChallengeEntity;
import com.soptie.server.persistence.repository.challenge.ChallengeRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class ChallengeStore {

	private final ChallengeRepository challengeRepository;

	@Getter
	@GlobalData
	private Map<Long, Challenge> challenges;

	private LocalDate updateDate;

	@PostConstruct
	@Scheduled(cron = "0 40 */1 * * *")
	public void init() {
		if (MapUtils.isNotEmpty(this.challenges) && LocalDate.now().equals(this.updateDate)) {
			return;
		}

		try {
			this.challenges = getNewChallenges();
			this.updateDate = LocalDate.now();
			log.info("[ChallengeStore] successfully updated. count={}", challenges.size());
		} catch (Exception e) {
			this.challenges = new HashMap<>();
			this.updateDate = null;
			log.error("[ChallengeStore] not updated.", e);
		}
	}

	public Challenge get(long id) {
		if (MapUtils.isEmpty(this.challenges)) {
			return getFallback(id);
		}

		return challenges.getOrDefault(id, getFallback(id));
	}

	public List<Challenge> getAll() {
		return challenges.values().stream().toList();
	}

	private Map<Long, Challenge> getNewChallenges() {
		return challengeRepository.findAll()
			.stream()
			.map(ChallengeEntity::toDomain)
			.map(challenge -> Pair.of(challenge.getId(), challenge))
			.filter(routine -> routine.getFirst() != null && routine.getSecond() != null)
			.collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
	}

	private Challenge getFallback(long id) {
		return challengeRepository.findById(id)
			.map(ChallengeEntity::toDomain)
			.orElse(null);
	}
}
