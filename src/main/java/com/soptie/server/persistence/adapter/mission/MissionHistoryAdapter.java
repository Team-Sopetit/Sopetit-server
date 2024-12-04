package com.soptie.server.persistence.adapter.mission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.soptie.server.domain.challenge.Mission;
import com.soptie.server.domain.membermission.MemberMission;
import com.soptie.server.domain.membermission.MissionHistory;
import com.soptie.server.persistence.entity.mission.MissionHistoryEntity;
import com.soptie.server.persistence.repository.mission.MissionHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MissionHistoryAdapter {
	private final MissionHistoryRepository historyRepository;

	public void save(final MemberMission memberMission, final Mission mission) {
		historyRepository.save(new MissionHistoryEntity(memberMission, mission));
	}

	public void deleteById(long historyId) {
		historyRepository.deleteById(historyId);
	}

	public boolean isExistByMemberIdAndCreatedAt(final long memberId, final LocalDate date) {
		return historyRepository.findByMemberIdAndCreatedAt(memberId, date).isPresent();
	}

	public List<MissionHistory> findAllByMemberIdAndCreatedAtBetween(
		final long memberId,
		final LocalDateTime startDateTime,
		final LocalDateTime endDateTime
	) {
		return historyRepository.findAllByMemberIdAndCreatedAtBetween(memberId, startDateTime, endDateTime).stream()
			.map(MissionHistoryEntity::toDomain).toList();
	}
}
