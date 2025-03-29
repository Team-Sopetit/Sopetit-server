package com.soptie.server.domain.challenge.util;

import org.springframework.stereotype.Component;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.persistence.adapter.challenge.ChallengeHistoryAdapter;
import com.soptie.server.persistence.adapter.challenge.MemberChallengeAdapter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChallengeValidator {

	private final MemberChallengeAdapter memberChallengeAdapter;
	private final ChallengeHistoryAdapter challengeHistoryAdapter;

	public void validChallenge(long memberId, long challengeId) {
		checkChallengedAchieved(memberId, challengeId);
		checkChallengeNotExists(memberId);
	}

	private void checkChallengedAchieved(long memberId, long challengeId) {
		if (challengeHistoryAdapter.isExistByMemberIdAndChallengeId(memberId, challengeId)) {
			throw new SoftieException(ExceptionCode.BAD_REQUEST, "오늘 이미 달성한 챌린지");
		}
	}

	private void checkChallengeNotExists(long memberId) {
		if (memberChallengeAdapter.findByMember(memberId).isPresent()) {
			throw new SoftieException(ExceptionCode.BAD_REQUEST, "챌린지가 존재하는 회원");
		}
	}
}
