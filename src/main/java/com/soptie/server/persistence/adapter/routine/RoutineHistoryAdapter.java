package com.soptie.server.persistence.adapter.routine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.memberroutine.RoutineHistory;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.entity.routine.RoutineHistoryEntity;
import com.soptie.server.persistence.repository.routine.RoutineHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoutineHistoryAdapter {
	private final RoutineHistoryRepository historyRepository;
	private final RoutineHistoryRepository routineHistoryRepository;

	public void save(final MemberRoutine memberRoutine, final Routine routine) {
		historyRepository.save(new RoutineHistoryEntity(memberRoutine, routine));
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

	public List<RoutineHistory> findAllByMemberIdAndCreatedAtBetween(
		final long memberId,
		final LocalDateTime startDateTime,
		final LocalDateTime endDateTime
	) {
		return historyRepository.findAllByMemberIdAndCreatedAtBetween(memberId, startDateTime, endDateTime).stream()
			.map(RoutineHistoryEntity::toDomain).toList();
	}

	public RoutineHistory findById(final long id) {
		return routineHistoryRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "RoutineHistoryId: " + id))
			.toDomain();
	}
}
