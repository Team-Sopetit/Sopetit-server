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
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.entity.routine.RoutineEntity;
import com.soptie.server.persistence.repository.routine.RoutineRepository;

import io.jsonwebtoken.lang.Collections;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoutineStore {

	private final RoutineRepository routineRepository;

	@Getter
	@GlobalData
	private Map<Long, Routine> routines;

	private LocalDate updateDate;

	@Scheduled(cron = "0 */10 * * * *")
	public void init() {
		if (!routines.isEmpty() && updateDate.isEqual(LocalDate.now())) {
			return;
		}

		try {
			update();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			routines = new HashMap<>();
		}
	}

	public Routine get(long id) {
		if (Collections.isEmpty(routines)) {
			update();
		}

		if (!routines.containsKey(id)) {
			return null;
		}

		return routines.get(id);
	}

	public List<Routine> getAll() {
		return routines.values().stream().toList();
	}

	private void update() {
		routines = routineRepository.findAll()
			.stream()
			.map(RoutineEntity::toDomain)
			.map(routine -> Pair.of(routine.getId(), routine))
			.filter(routine -> routine.getFirst() != null && routine.getSecond() != null)
			.collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));

		updateDate = LocalDate.now();
	}
}
