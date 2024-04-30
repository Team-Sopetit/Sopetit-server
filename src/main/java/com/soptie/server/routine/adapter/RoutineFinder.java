package com.soptie.server.routine.adapter;

import static com.soptie.server.routine.entity.RoutineType.*;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.member.entity.Member;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.repository.RoutineRepository;
import com.soptie.server.theme.entity.Theme;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class RoutineFinder {

	private final RoutineRepository routineRepository;

	public List<Routine> findDailyRoutinesByThemeIds(List<Long> themeIds) {
		return routineRepository.findByTypeAndThemeIds(DAILY, themeIds);
	}

	public List<Routine> findDailyRoutinesByThemeAndNotMember(Theme theme, Member member) {
		return routineRepository.findByTypeAndThemeAndNotMember(DAILY, theme, member);
	}
}
