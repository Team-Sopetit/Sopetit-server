package com.soptie.server.routine.repository;

import java.util.List;

import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.RoutineType;

public interface RoutineCustomRepository {
	List<Routine> findByTypeAndThemeIds(RoutineType type, List<Long> themeIds);

	List<Routine> findByTypeAndThemeAndNotMember(long memberId, RoutineType type, long themeId);

	List<Routine> findByTypeAndThemeId(RoutineType type, Long themeId);

	List<Routine> findByIdsAndType(List<Long> ids, RoutineType type);
}
