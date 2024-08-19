package com.soptie.server.persistence.adapter;

import static com.soptie.server.common.message.RoutineErrorCode.*;

import java.util.List;

import com.soptie.server.common.exception.RoutineException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.entity.RoutineEntity;
import com.soptie.server.persistence.repository.RoutineRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class RoutineAdapter {
	private final RoutineRepository routineRepository;

	public List<Routine> findByThemeId(long themeId) {
		return routineRepository.findByThemeIdOrderByContentAsc(themeId).stream()
			.map(RoutineEntity::toDomain)
			.toList();
	}

	public List<Routine> findAllByTypeAndThemeIds(RoutineType type, List<Long> themeIds) {
		return routineRepository.findByTypeAndThemeIds(type, themeIds).stream().map(RoutineVO::from).toList();
	}

	public List<Routine> findAllNotInMemberByTypeAndThemeId(long memberId, RoutineType type, long themeId) {
		return routineRepository.findByTypeAndThemeAndNotMember(memberId, type, themeId)
			.stream().map(RoutineVO::from).toList();
	}

	public Routine findById(long id) {
		return routineRepository.findById(id)
			.orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
	}

	public List<Routine> findDailyByIds(List<Long> ids) {
		return routineRepository.findByIdsAndType(ids, RoutineType.DAILY);
	}

	public List<Routine> findChallengeRoutinesByTheme(Long themeId) {
		return routineRepository.findByTypeAndThemeId(RoutineType.CHALLENGE, themeId);
	}
}
