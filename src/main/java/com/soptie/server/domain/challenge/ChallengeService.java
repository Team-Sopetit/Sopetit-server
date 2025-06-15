package com.soptie.server.domain.challenge;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.challenge.dto.ChallengesResponse;
import com.soptie.server.persistence.adapter.challenge.ChallengeAdapter;
import com.soptie.server.persistence.adapter.challenge.MemberChallengeAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeService {
	private final ChallengeAdapter challengeAdapter;
	private final MemberChallengeAdapter memberChallengeAdapter;

	public ChallengesResponse getChallengesByTheme(long memberId, long themeId) {
		val challenges = challengeAdapter.findAllByTheme(themeId);
		val challengeIdInMember = memberChallengeAdapter.findByMember(memberId)
			.map(MemberChallenge::getChallengeId)
			.orElse(null);
		return ChallengesResponse.of(challenges, challengeIdInMember);
	}

}
