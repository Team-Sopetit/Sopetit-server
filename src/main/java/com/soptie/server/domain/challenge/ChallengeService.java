package com.soptie.server.domain.challenge;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.response.challenge.ChallengesResponse;
import com.soptie.server.domain.membermission.MemberChallenge;
import com.soptie.server.persistence.adapter.challenge.ChallengeAdapter;
import com.soptie.server.persistence.adapter.mission.MemberChallengeAdapter;

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
		val challengeIdsInMember = memberChallengeAdapter.findAllByMember(memberId)
			.stream().map(MemberChallenge::getId)
			.toList();
		return ChallengesResponse.of(challenges, challengeIdsInMember);
	}

}
