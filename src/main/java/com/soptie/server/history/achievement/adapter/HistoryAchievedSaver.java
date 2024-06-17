package com.soptie.server.history.achievement.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.history.achievement.entity.HistoryAchieved;
import com.soptie.server.history.achievement.repository.HistoryAchievedRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class HistoryAchievedSaver {

	private final HistoryAchievedRepository achievedRepository;

	public void save(HistoryAchieved achieved) {
		achievedRepository.save(achieved);
	}
}
