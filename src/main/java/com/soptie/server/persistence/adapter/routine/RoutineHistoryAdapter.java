package com.soptie.server.persistence.adapter.routine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.memberroutine.RoutineHistory;
import com.soptie.server.persistence.entity.routine.RoutineHistoryEntity;
import com.soptie.server.persistence.repository.routine.RoutineHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoutineHistoryAdapter {

	private final RoutineHistoryRepository historyRepository;

	/**
	 * save
	 */
	public void save(final MemberRoutine memberRoutine) {
		historyRepository.save(new RoutineHistoryEntity(memberRoutine));
	}

	/**
	 * delete
	 */
	public void deleteById(long id) {
		historyRepository.deleteById(id);
	}

	public void deleteByRoutineId(long routineId) {
		historyRepository.deleteByRoutineId(routineId);
	}

	/**
	 * find
	 */
	public boolean isExistByMemberIdAndCreatedAt(final long memberId, final LocalDate date) {
		return historyRepository.findByMemberIdAndCreatedAt(memberId, date).isPresent();
	}

	public List<RoutineHistory> findAllByMemberIdAndCreatedAtBetween(
		final long memberId,
		final LocalDateTime startDateTime,
		final LocalDateTime endDateTime
	) {
		return historyRepository.findAllByMemberIdAndCreatedAtBetween(memberId, startDateTime, endDateTime).stream()
			.map(RoutineHistoryEntity::toDomain).toList();
	}

	public RoutineHistory findById(final long id) {
		return historyRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "RoutineHistoryId: " + id))
			.toDomain();
	}

	public void deleteAllByMemberId(final long memberId) {
		historyRepository.deleteAllByMemberId(memberId);
	}
}
