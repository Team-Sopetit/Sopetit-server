package com.soptie.server.common.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mysema.commons.lang.Pair;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.MemberChallenge;
import com.soptie.server.persistence.adapter.challenge.ChallengeAdapter;

public class ChallengeUtils {

	public static Map<Long /* challenge id */, Challenge> getChallengesMap(
		ChallengeAdapter challengeAdapter,
		List<MemberChallenge> memberChallenges
	) {
		List<Long> challengeIds = memberChallenges
			.stream()
			.map(MemberChallenge::getChallengeId)
			.toList();

		return challengeAdapter.findByIds(challengeIds)
			.stream()
			.map(challenge -> Pair.of(challenge.getId(), challenge))
			.filter(pair -> pair.getFirst() != null && pair.getSecond() != null)
			.collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
	}
}
