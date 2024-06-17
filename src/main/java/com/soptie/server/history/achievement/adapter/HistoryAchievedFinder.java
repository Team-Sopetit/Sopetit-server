package com.soptie.server.history.achievement.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.history.achievement.repository.HistoryAchievedRepository;
import com.soptie.server.memberRoutine.entity.MemberRoutine;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class HistoryAchievedFinder {

	private final HistoryAchievedRepository achievedRepository;

	public boolean isAchievedToday(MemberRoutine routine) {
		return achievedRepository.isAchievedToday(routine);
	}
}
