package com.soptie.server.routine.adapter;

import static com.soptie.server.routine.message.RoutineErrorCode.*;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.RoutineType;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.RoutineRepository;
import com.soptie.server.routine.service.vo.RoutineVO;

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

	public List<Routine> findChallengeRoutinesByTheme(Long themeId) {
		return routineRepository.findByTypeAndThemeId(RoutineType.CHALLENGE, themeId);
	}

	public List<RoutineVO> findAllByTypeAndThemeId(RoutineType type, Long themeId) {
		return routineRepository.findByTypeAndThemeId(type, themeId).stream().map(RoutineVO::from).toList();
	}
}
