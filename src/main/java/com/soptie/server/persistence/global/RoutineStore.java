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
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.entity.routine.RoutineEntity;
import com.soptie.server.persistence.repository.routine.RoutineRepository;

import io.jsonwebtoken.lang.Collections;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class RoutineStore {

	private final RoutineRepository routineRepository;

	@Getter
	@GlobalData
	private Map<Long, Routine> routines;

	private LocalDate updateDate;

	@PostConstruct
	@Scheduled(cron = "0 20 */1 * * *")
	public void init() {
		if (MapUtils.isNotEmpty(this.routines) && LocalDate.now().equals(this.updateDate)) {
			return;
		}

		try {
			this.routines = getNewRoutines();
			this.updateDate = LocalDate.now();
			log.info("[RoutineStore] successfully updated. count={}", routines.size());
		} catch (Exception e) {
			this.routines = new HashMap<>();
			this.updateDate = null;
			log.error("[RoutineStore] not updated.", e);
		}
	}

	public Routine get(long id) {
		if (Collections.isEmpty(routines)) {
			return getFallback(id);
		}
		return routines.getOrDefault(id, getFallback(id));
	}

	public List<Routine> getAll() {
		return routines.values().stream().toList();
	}

	private Map<Long, Routine> getNewRoutines() {
		return routineRepository.findAll()
			.stream()
			.map(RoutineEntity::toDomain)
			.map(routine -> Pair.of(routine.getId(), routine))
			.filter(routine -> routine.getFirst() != null && routine.getSecond() != null)
			.collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
	}

	private Routine getFallback(long id) {
		return routineRepository.findById(id)
			.map(RoutineEntity::toDomain)
			.orElse(null);
	}
}
