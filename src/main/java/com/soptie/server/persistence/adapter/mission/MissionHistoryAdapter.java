package com.soptie.server.persistence.adapter.mission;

import org.springframework.stereotype.Component;

import com.soptie.server.persistence.entity.mission.MissionHistoryEntity;
import com.soptie.server.persistence.repository.mission.MissionHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MissionHistoryAdapter {
	private final MissionHistoryRepository historyRepository;

	public void save(long memberMissionId) {
		historyRepository.save(new MissionHistoryEntity(memberMissionId));
	}

	public void deleteById(long historyId) {
		historyRepository.deleteById(historyId);
	}
}
