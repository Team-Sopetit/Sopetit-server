package com.soptie.server.persistence.adapter.mission;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.soptie.server.persistence.entity.mission.MissionHistoryEntity;
import com.soptie.server.persistence.repository.mission.MissionHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MissionHistoryAdapter {
	private final MissionHistoryRepository historyRepository;

	public void save(long memberMissionId, long memberId) {
		historyRepository.save(new MissionHistoryEntity(memberMissionId, memberId));
	}

	public void deleteById(long historyId) {
		historyRepository.deleteById(historyId);
	}

	public boolean isExistByMemberIdAndCreatedAt(final long memberId, final LocalDate date) {
		return historyRepository.findByMemberIdAndCreatedAt(memberId, date).isPresent();
	}
}
