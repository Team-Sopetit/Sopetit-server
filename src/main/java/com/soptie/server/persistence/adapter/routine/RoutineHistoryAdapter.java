package com.soptie.server.persistence.adapter.routine;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.soptie.server.persistence.entity.routine.RoutineHistoryEntity;
import com.soptie.server.persistence.repository.routine.RoutineHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoutineHistoryAdapter {
	private final RoutineHistoryRepository historyRepository;

	public void save(long memberRoutineId) {
		historyRepository.save(new RoutineHistoryEntity(memberRoutineId));
	}

	public void deleteByRoutineIdAndCreatedAt(long memberRoutineId, LocalDate date) {
		historyRepository.deleteByMemberRoutineIdAndCreatedAt(memberRoutineId, date);
	}

	public void deleteById(long id) {
		historyRepository.deleteById(id);
	}
}
