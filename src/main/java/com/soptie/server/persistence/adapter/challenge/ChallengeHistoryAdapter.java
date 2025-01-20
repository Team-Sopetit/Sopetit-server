package com.soptie.server.persistence.adapter.challenge;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.ChallengeHistory;
import com.soptie.server.domain.challenge.MemberChallenge;
import com.soptie.server.persistence.entity.challenge.ChallengeHistoryEntity;
import com.soptie.server.persistence.repository.challenge.ChallengeHistoryRepository;

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

	public List<ChallengeHistory> findAllByMemberIdAndCreatedAtBetween(
		final long memberId,
		final LocalDateTime startDateTime,
		final LocalDateTime endDateTime
	) {
		return historyRepository.findAllByMemberIdAndCreatedAtBetween(memberId, startDateTime, endDateTime).stream()
			.map(ChallengeHistoryEntity::toDomain).toList();
	}

	public ChallengeHistory findById(final long id) {
		return historyRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "ChallengeHistoryId: " + id))
			.toDomain();
	}
}
