package com.soptie.server.persistence.adapter.challenge;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.membermission.MemberChallenge;
import com.soptie.server.domain.membermission.MissionHistory;
import com.soptie.server.persistence.entity.mission.ChallengeHistoryEntity;
import com.soptie.server.persistence.repository.mission.ChallengeHistoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChallengeHistoryAdapter {
	private final ChallengeHistoryRepository historyRepository;

	public void save(final MemberChallenge memberChallenge, final Challenge challenge) {
		historyRepository.save(new ChallengeHistoryEntity(memberChallenge, challenge));
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
			.map(ChallengeHistoryEntity::toDomain).toList();
	}
}
