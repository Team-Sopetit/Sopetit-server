package com.soptie.server.persistence.adapter;

import static com.soptie.server.common.message.RoutineErrorCode.*;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.common.exception.RoutineException;
import com.soptie.server.persistence.repository.RoutineRepository;
import com.soptie.server.domain.routine.RoutineVO;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class RoutineFinder {

	private final RoutineRepository routineRepository;

	public List<RoutineVO> findAllByTypeAndThemeIds(RoutineType type, List<Long> themeIds) {
		return routineRepository.findByTypeAndThemeIds(type, themeIds).stream().map(RoutineVO::from).toList();
	}

	public List<RoutineVO> findAllNotInMemberByTypeAndThemeId(long memberId, RoutineType type, long themeId) {
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

	public List<Routine> findAllByTypeAndThemeId(RoutineType type, Long themeId) {
		return routineRepository.findByTypeAndThemeId(type, themeId);
	}
}
