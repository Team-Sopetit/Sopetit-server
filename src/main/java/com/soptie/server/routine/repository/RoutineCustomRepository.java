package com.soptie.server.routine.repository;

import java.util.List;

import com.soptie.server.member.entity.Member;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.RoutineType;
import com.soptie.server.theme.entity.Theme;

public interface RoutineCustomRepository {
	List<Routine> findByTypeAndThemeIds(RoutineType type, List<Long> themeIds);
	List<Routine> findByTypeAndThemeAndNotMember(RoutineType type, Theme theme, Member member);
	List<Routine> findByTypeAndThemeId(RoutineType type, Long themeId);
}
