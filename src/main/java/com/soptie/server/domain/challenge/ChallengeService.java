package com.soptie.server.domain.challenge;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.response.challenge.GetChallengesByMemberResponse;
import com.soptie.server.domain.membermission.MemberMission;
import com.soptie.server.persistence.adapter.mission.ChallengeAdapter;
import com.soptie.server.persistence.adapter.mission.MemberMissionAdapter;
import com.soptie.server.persistence.adapter.mission.MissionAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChallengeService {
	private final ChallengeAdapter challengeAdapter;
	private final MissionAdapter missionAdapter;
	private final MemberMissionAdapter memberMissionAdapter;

	public GetChallengesByMemberResponse getChallengesByTheme(long memberId, long themeId) {
		val challenges = challengeAdapter.findByThemeId(themeId);
		val missions = missionAdapter.findByChallengeIds(challenges.stream().map(Challenge::getId).toList());
		val missionIds = missions.stream().map(Mission::getId).toList();
		val memberMissionIds = memberMissionAdapter.findByMemberIdAndMissionIds(memberId, missionIds).stream()
			.map(MemberMission::getMissionId)
			.toList();
		return GetChallengesByMemberResponse.of(toChallengesByMember(challenges, missions, memberMissionIds));
	}

	private Map<Challenge, Map<Boolean, List<Mission>>> toChallengesByMember(
		List<Challenge> challenges,
		List<Mission> missions,
		List<Long> memberMissionIds
	) {
		return challenges.stream().collect(Collectors.toMap(
			// challenge-key
			challenge -> challenge,
			// map-value
			challenge -> missions.stream()
				.filter(mission -> mission.getChallengeId() == challenge.getId())
				.collect(Collectors.partitioningBy( // missionId in memberMissionIds 조건에 따라 true/false 분류
					mission -> memberMissionIds.contains(mission.getId())
				))
		));
	}
}
