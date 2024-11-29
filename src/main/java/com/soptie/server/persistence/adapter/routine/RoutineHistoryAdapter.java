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

	public void save(long memberRoutineId, long memberId) {
		historyRepository.save(new RoutineHistoryEntity(memberRoutineId, memberId));
	}

	public void deleteByRoutineIdAndCreatedAt(long memberRoutineId, LocalDate date) {
		historyRepository.deleteByMemberRoutineIdAndCreatedAt(memberRoutineId, date);
	}

	public void deleteById(long id) {
		historyRepository.deleteById(id);
	}

	public boolean isExistByMemberIdAndCreatedAt(final long memberId, final LocalDate date) {
		return historyRepository.findByMemberIdAndCreatedAt(memberId, date).isPresent();
	}
}
